# simple

https://www.rabbitmq.com/tutorials/tutorial-one-java.html

## Run Apps

### Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

sender output:
```
 [x] Sent 'Hello World!'
```

RabbitMQ

> go to http://localhost:15672/#/queues

> `Hello` queue has 1 Ready message

### Receiver

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

receiver output:
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'Hello World!'
```

RabbitMQ

> go to http://localhost:15672/#/queues

> `Hello` queue has 0 Ready message
