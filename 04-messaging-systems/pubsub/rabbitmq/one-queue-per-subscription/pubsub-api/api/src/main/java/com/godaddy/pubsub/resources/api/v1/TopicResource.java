package com.godaddy.pubsub.resources.api.v1;

import com.godaddy.pubsub.dataAccess.TopicNamingScheme;
import com.godaddy.pubsub.dataAccess.interfaces.MessageMiddlewareDriver;
import com.godaddy.pubsub.dataAccess.interfaces.SubscriptionsRepository;
import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.model.Message;
import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.messages.MessageTicket;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteStreams;
import io.swagger.annotations.*;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Path("/api/v1/topics")
@Api(tags = "Topics", description = "PubSub topics api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {
    private TopicNamingScheme topicNamingScheme;
    private TopicsRepository topicsRepository;
    private SubscriptionsRepository subscriptionsRepository;
    private MessageMiddlewareDriver messageMiddlewareDriver;

    @Inject
    public TopicResource(
            final TopicNamingScheme topicNamingScheme,
            final TopicsRepository topicsRepository,
            final SubscriptionsRepository subscriptionsRepository,
            final MessageMiddlewareDriver messageMiddlewareDriver){

        this.topicNamingScheme = topicNamingScheme;
        this.topicsRepository = topicsRepository;
        this.subscriptionsRepository = subscriptionsRepository;
        this.messageMiddlewareDriver = messageMiddlewareDriver;
    }

    @POST
    @ApiOperation(value = "Create a topic")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED", responseHeaders = {
                    @ResponseHeader(name = "Location")
            }),
            @ApiResponse(code = 422, message = "Invalid Topic Creation Request"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    public Response createTopic(@Valid final Topic topic){
        TopicId topicId = topicNamingScheme.buildTopicId(topic.getNameDescriptor());

        Topic builtTopic = topic.toBuilder()
                .topicId(topicId)
                .build();

        messageMiddlewareDriver.createTopic(builtTopic); // create Exchange
        topicsRepository.createTopic(builtTopic); // config store

        return Response.status(Response.Status.CREATED)
                .entity(builtTopic)
                .build();
    }

    @GET
    @ApiOperation(value = "Lists all topics")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TopicList.class),
            @ApiResponse(code = 500, message = "Server Error")
    })
    public Response getTopics(){

        ImmutableList<Topic> allTopics = topicsRepository.getAllTopics();
        return Response.ok(allTopics).build();
    }

    private interface TopicList extends List<Topic> {}

    @GET
    @ApiOperation(value = "Lists all subscriptions for a given topic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubscriptionList.class),
            @ApiResponse(code = 404, message = "Topic Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @Path("/{topicId}/subscription")
    public Response getTopicSubscriptions(@PathParam("topicId") final TopicId topicId){

        ImmutableList<Subscription> topicSubscriptions = subscriptionsRepository.getTopicsSubscriptions(topicId);
        return Response.ok(topicSubscriptions).build();
    }

    private interface SubscriptionList extends List<Subscription>{}

    @POST
    @ApiOperation(value = "Publish a message to a topic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "PUBLISHED", response = MessageTicket.class),
            @ApiResponse(code = 404, message = "Topic Not Found"),
            @ApiResponse(code = 500, message = "Server Error")
    })
    @Path("/{topicId}/messages")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response publishMessage(
            @PathParam("topicId") final TopicId topicId,
            @HeaderParam("X-Request-Id") final String requestId,
            final InputStream inputStream){

        try {
            Topic topic = topicsRepository.getTopic(topicId);
            final MessageId messageId = MessageId.random();
            Message message = Message.builder()
                    .topicId(topicId)
                    .timestamp(DateTime.now())
                    .messageId(messageId)
                    .requestId(requestId)
                    .data(ByteStreams.toByteArray(inputStream))
                    .build();
            messageMiddlewareDriver.publish(topic, message);

            MessageTicket messageTicket = MessageTicket.builder()
                    .messageId(messageId)
                    .build();

            return Response.ok(messageTicket).build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
