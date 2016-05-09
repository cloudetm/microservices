package com.godaddy.pubsub.componentTests;

import com.godaddy.pubsub.pub.client.ServiceClient;
import com.godaddy.pubsub.pub.model.errors.ErrorEntity;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriberMessageDeliveryUri;
import com.godaddy.pubsub.pub.model.subscriptions.Subscription;
import com.godaddy.pubsub.pub.model.subscriptions.SubscriptionId;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import com.godaddy.pubsub.resources.api.v1.SubscriptionResource;
import com.godaddy.pubsub.serialization.JacksonJsonMapper;
import org.junit.Test;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionTests extends TestBase {
    private static final String DESCRIPTION = "test description";
    private static final TopicId NON_EXISTING_TOPIC_ID = TopicId.valueOf("some random topic id");
    private static final SubscriberMessageDeliveryUri RANDOM_URI = SubscriberMessageDeliveryUri
            .valueOf("http://www.hahaha.com");

    private int topicVersion = 1;

    @Test
    public void create_subscription_valid_request() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                .description(DESCRIPTION)
                .topicId(createTopic())
                .messageDeliveryUri(RANDOM_URI)
                .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationSuccess(response, 201, subscriptionToCreate);
    }

    @Test
    public void create_subscription_missing_description() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                        .topicId(createTopic())
                        .messageDeliveryUri(RANDOM_URI)
                        .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationSuccess(response, 201, subscriptionToCreate);
    }

    @Test
    public void create_subscription_with_subscription_id() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                .description(DESCRIPTION)
                .topicId(createTopic())
                .messageDeliveryUri(RANDOM_URI)
                .subscriptionId(SubscriptionId.random())
                .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationSuccess(response, 201, subscriptionToCreate);
    }

    @Test
    public void create_subscription_missing_topic_id() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                .description(DESCRIPTION)
                .messageDeliveryUri(RANDOM_URI)
                .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationFailure(response, 422);
    }

    @Test
    public void create_subscription_missing_message_delivery_uri() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                .topicId(createTopic())
                .description(DESCRIPTION)
                .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationFailure(response, 422);
    }

    @Test
    public void create_subscription_non_existing_topic_id() throws IOException {
        Subscription subscriptionToCreate = Subscription.builder()
                .description(DESCRIPTION)
                .topicId(NON_EXISTING_TOPIC_ID)
                .messageDeliveryUri(RANDOM_URI)
                .build();

        final Response<Subscription> response = getCreateResponse(subscriptionToCreate);
        verifyCreationFailure(response, 422, SubscriptionResource.ERROR_CODE_TOPIC_NOT_FOUND,
                SubscriptionResource.ERROR_MESSAGE_TOPIC_NOT_FOUND);
    }

    private Response<Subscription> getCreateResponse(Subscription subscription) throws IOException {
        final ServiceClient client = createServiceClient();

        Call<Subscription> subscriptionCreate = client.createSubscription(subscription);

        return subscriptionCreate.execute();
    }

    private void verifyCreationSuccess(Response<Subscription> response, Integer expectedStatusCode,
                                       Subscription subscriptionToCreate) {
        Subscription body = response.body();

        assertThat(response.code()).isEqualTo(expectedStatusCode);
        assertThat(body.getSubscriptionId()).isNotNull();
        assertThat(body.getSubscriptionId()).isNotEqualTo(subscriptionToCreate.getSubscriptionId());
        assertThat(body.getTopicId()).isEqualTo(subscriptionToCreate.getTopicId());
        assertThat(body.getDescription()).isEqualTo(subscriptionToCreate.getDescription());
        assertThat(body.getMessageDeliveryUri()).isEqualTo(subscriptionToCreate.getMessageDeliveryUri());
    }

    private void verifyCreationFailure(Response<Subscription> response, Integer expectedStatusCode) {
        assertThat(response.code()).isEqualTo(expectedStatusCode);
    }

    private TopicId createTopic() throws IOException {
        return createServiceClient().createTopic(
                Topic.builder()
                        .nameDescriptor(TopicNameDescriptor.builder()
                                .team("erp")
                                .project("pubsub")
                                .entity("test")
                                .eventType("started")
                                .majorVersion(topicVersion++)
                                .build()
                        ).build()
        ).execute().body().getTopicId();
    }


    private void verifyCreationFailure(Response<Subscription> response, Integer expectedStatusCode,
                                       String expectedErrorCode, String expectedMessage) throws IOException {
        verifyCreationFailure(response, expectedStatusCode);

        JacksonJsonMapper mapper = new JacksonJsonMapper();
        ErrorEntity error = mapper.fromJson(response.errorBody().string(), ErrorEntity.class);

        assertThat(error.getCode()).isEqualTo(expectedErrorCode);
        assertThat(error.getMessage()).isEqualTo(expectedMessage);
    }
}
