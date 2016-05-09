package com.godaddy.pubsub.unittests.topics;

import com.godaddy.pubsub.dataAccess.InMemoryTopicsRepository;
import com.godaddy.pubsub.dataAccess.TopicNamingScheme;
import com.godaddy.pubsub.dataAccess.interfaces.TopicsRepository;
import com.godaddy.pubsub.pub.model.topics.Topic;
import com.godaddy.pubsub.pub.model.topics.TopicNameDescriptor;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class TopicsRespositoryTests {
    private final TopicNamingScheme topicNamingScheme = new TopicNamingScheme();

    private final TopicsRepository repository = new InMemoryTopicsRepository();

    private final PodamFactory fixture = new PodamFactoryImpl();

    @Test
    public void afterAddingATopicItShouldBeGettable() {

        Topic topic = createTopicWithVersion(1);

        repository.createTopic(topic);

        final Topic repositoryTopic = repository.getTopic(topic.getTopicId());

        assertThat(repositoryTopic)
            .withFailMessage("Topic not found for added topic id: " + topic.getTopicId())
            .isNotNull();
    }

    @Test
    public void afterAddingManyTopicsTheyShouldAllBeReturnedFromGetAll() {

        final ImmutableList<Topic> topics = ImmutableList.of(
            createTopicWithVersion(1),
            createTopicWithVersion(2),
            createTopicWithVersion(3),
            createTopicWithVersion(4));


        topics.forEach(repository::createTopic);

        final ImmutableList<Topic> allTopics = repository.getAllTopics();

        assertThat(allTopics)
            .containsAll(topics)
            .hasSize(topics.size());
    }

    @Test
    public void afterDeletingATopicItShouldNotBeReturned() {
        Topic topic = createTopicWithVersion(1);

        repository.createTopic(topic);

        {
            final Topic repositoryTopic = repository.getTopic(topic.getTopicId());

            assertThat(repositoryTopic)
                .withFailMessage("Topic not found for added topic id: " + topic.getTopicId())
                .isNotNull();
        }

        repository.deleteTopic(topic.getTopicId());

        {
            final Topic repositoryTopic = repository.getTopic(topic.getTopicId());

            assertThat(repositoryTopic).isNull();
        }

    }

    private Topic createTopicWithVersion(final int majorVersion) {

        final TopicNameDescriptor nameDescriptor =
            fixture.manufacturePojo(TopicNameDescriptor.class)
                   .toBuilder()
                   .majorVersion(majorVersion)
                   .build();

        return Topic.builder()
                    .nameDescriptor(nameDescriptor)
                    .description("A topic")
                    .topicId(topicNamingScheme.buildTopicId(nameDescriptor))
                    .build();

    }
}