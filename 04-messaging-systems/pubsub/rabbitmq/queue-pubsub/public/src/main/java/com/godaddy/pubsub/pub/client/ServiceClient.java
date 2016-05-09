package com.godaddy.pubsub.pub.client;

import com.godaddy.pubsub.pub.Mappers;
import com.godaddy.pubsub.pub.model.messages.MessageId;
import com.godaddy.pubsub.pub.model.messages.MessageTicket;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.google.common.collect.ImmutableList;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import org.joda.time.DateTime;
import retrofit.Call;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.*;

public interface ServiceClient {
    static ServiceClient createClient(String baseUri) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(baseUri)
                        .addConverterFactory(JacksonConverterFactory.create(Mappers.json()))
                        .build();

        ServiceClient service = retrofit.create(ServiceClient.class);

        return service;
    }

    @GET("api/v1/topics")
    Call<ImmutableList<Topic>> getTopics();

    @GET("api/v1/topics/{topicId}/subscriptions")
    Call<ImmutableList<Subscription>> getTopicSubscriptions(@Path("topicId") TopicId topicId);

    @POST("api/v1/topics")
    Call<Topic> createTopic(@Body final Topic topic);

    @POST("api/v1/topics/{topicId}/messages")
    Call<MessageTicket> publishMessage(@Path("topicId") final TopicId topicId,
                                       @Header("X-Request-Id") final String requestId,
                                       @Body final RequestBody messageBody);

    @POST("api/v1/subscriptions")
    Call<Subscription> createSubscription(@Body final Subscription subscription);

    @POST
    Call<ResponseBody> deliver(
            @Url String uri,
            @Header("X-PubSub-TopicId") TopicId topicId,
            @Header("X-PubSub-Subscription-Id") SubscriptionId subscriptionId,
            @Header("X-PubSub-Timestamp") DateTime timestamp,
            @Header("X-PubSub-Message-Id") MessageId messageId,
            @Header("X-Request-Id") String requestId,
            @Body RequestBody messageData);
}
