package com.godaddy.pubsub.resources.api.v1;

import com.godaddy.pubsub.dataAccess.TopicNamingScheme;
import com.godaddy.pubsub.dataAccess.interfaces.MessageRepository;
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
import com.google.inject.Inject;
import io.swagger.annotations.*;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Path("/api/v1/topics")
@Api(tags = "Topics", description = "PubSub topics api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopicResource {

    private final TopicsRepository topicsRepository;
    private final TopicNamingScheme topicNamingScheme;
    private final SubscriptionsRepository subscriptionsRepository;
    private final MessageRepository messageRepository;

    @Inject
    public TopicResource(final TopicNamingScheme topicNamingScheme,
                         final TopicsRepository topicsRepository,
                         final SubscriptionsRepository subscriptionsRepository,
                         final MessageRepository messageRepository) {
        this.topicNamingScheme = topicNamingScheme;
        this.topicsRepository = topicsRepository;
        this.subscriptionsRepository = subscriptionsRepository;
        this.messageRepository = messageRepository;
    }

    @GET
    @ApiOperation(value = "Lists all topics")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TopicList.class),
            @ApiResponse(code = 500, message = "Server Error")
    })
    public Response getTopics() {

        ImmutableList<Topic> allTopics = topicsRepository.getAllTopics();

        return Response.ok(allTopics).build();
    }

    private interface TopicList extends List<Topic> {
    }

    @GET
    @ApiOperation(value = "Lists all subscriptions for a given topic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubscriptionList.class),
            @ApiResponse(code = 404, message = "Topic Not Found"),
            @ApiResponse(code = 500, message = "Server Error")

    })
    @Path("/{topicId}/subscriptions")
    public Response getTopicSubscriptions(@PathParam("topicId") final TopicId topicId) {

        ImmutableList<Subscription> topicSubscriptions = subscriptionsRepository.getTopicSubscriptions(topicId);

        return Response.ok(topicSubscriptions).build();
    }

    private interface SubscriptionList extends List<Subscription> {
    }

    @POST
    @ApiOperation(value = "Publish a message to a topic")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "PUBLISHED", response = MessageTicket.class),
            @ApiResponse(code = 404, message = "Topic Not Found"),
            @ApiResponse(code = 500, message = "Server Error")

    })
    @Path("/{topicId}/messages")
    @Consumes({MediaType.WILDCARD})
    public Response publishMessage(
            @PathParam("topicId") final TopicId topicId,
            @HeaderParam("X-Request-Id") final String requestId,
            final InputStream inputStream) throws IOException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        Topic topic = topicsRepository.getTopic(topicId);
        Message message = Message.builder()
                .timestamp(DateTime.now())
                .messageId(MessageId.random())
                .requestId(requestId)
                .data(ByteStreams.toByteArray(inputStream))
                .build();
        messageRepository.publish(topic, message);

        MessageTicket messageTicket = MessageTicket.builder()
                .messageId(message.getMessageId())
                .build();

        return Response.ok(messageTicket).build();
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
    public Response createTopic(@Valid final Topic topic) {

        TopicId topicId = topicNamingScheme.buildTopicId(topic.getNameDescriptor());

        Topic builtTopic = topic.toBuilder()
                .topicId(topicId)
                .build();

        messageRepository.createTopic(builtTopic);
        topicsRepository.createTopic(builtTopic);

        return Response.status(Response.Status.CREATED)
                .entity(builtTopic)
                .build();
    }
}
