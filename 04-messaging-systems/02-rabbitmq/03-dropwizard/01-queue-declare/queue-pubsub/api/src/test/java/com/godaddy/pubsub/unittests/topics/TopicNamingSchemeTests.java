package com.godaddy.pubsub.unittests.topics;

import com.godaddy.pubsub.dataAccess.TopicNamingScheme;
import com.godaddy.pubsub.pub.model.topics.TopicId;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicNamingSchemeTests {
    private final TopicNamingScheme namingScheme = new TopicNamingScheme();

    @Test
    public void verifyNamedAsExpected() {
        TopicNameDescriptor nameDescriptor =
            TopicNameDescriptor.builder()
                               .team("ecomm")
                               .project("fulfillment")
                               .entity("Order") // tests lower casing
                               .eventType("Created")
                               .majorVersion(1)
                               .build();

        final TopicId topicId = namingScheme.buildTopicId(nameDescriptor);

        assertThat(topicId.get())
            .isEqualTo("ecomm.fulfillment.order.created.v1");
    }

    @Test
    public void verifyMissingEntityNamedAsExpected() {
        TopicNameDescriptor nameDescriptor =
            TopicNameDescriptor.builder()
                               .team("ecomm")
                               .project("fulfillment")
                               .entity(null)
                               .eventType("Created")
                               .majorVersion(1)
                               .build();

        final TopicId topicId = namingScheme.buildTopicId(nameDescriptor);

        assertThat(topicId.get())
            .isEqualTo("ecomm.fulfillment.created.v1");
    }
}
