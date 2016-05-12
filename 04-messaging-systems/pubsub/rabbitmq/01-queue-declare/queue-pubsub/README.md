# Pubsub rabbitmq

https://www.rabbitmq.com/tutorials/tutorial-one-java.html

An API for asynchronous, durable pub-sub messaging.

## Run Test

> delete the queue is associated to the topic on RabbitMQ UI if the queue is already exist.

> TopicComponentTests.publish_message()

## Run app local via Docker

```
./scripts/run-core.sh
```

## Application Configuration

```
Program arguments: server docker/data/conf/configuration.yml
Working directory: add "/api" after "pubsub-api"
Check "Single instance only"
```

## generate dropwizard project

> `$ mvn archetype:generate -Dfilter=dropwizard`

```
Choose archetype:
1: remote -> br.com.ingenieux:elasticbeanstalk-docker-dropwizard-webapp-archetype (A Maven Archetype for Publishing Dropwizard-based Services on AWS' Elastic
        Beanstalk Service)
2: remote -> io.paradoxical:dropwizard-api (A simple dropwizard (0.9.1) api template with swagger enabled, bundled as a docker container)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 2
Choose io.paradoxical:dropwizard-api version:
1: 0.9.2
2: 1.0
Choose a number: 2:
```
