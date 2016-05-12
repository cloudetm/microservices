# publish-subscribe

We can use a fanout exchange and bind any number of queues to that exchange regardless of the binding key.
The Receiver class can be used to bind a specified queue to a fanout exchange and receive messages from it.

## Run Apps

### QueueingConsumer

#### Permanent

Receiver

> `cd queueingconsumer-nextdelivery/receiver-pubsub-permanent-queue/`

> build `$ mvn clean install`


Receiver1 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Receiver2 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

output: only one of QueueingConsumer receivers receives the message
```
my message
```

#### Temporary

Receiver

> `cd queueingconsumer-nextdelivery/receiver-pubsub-temporary-queue/`

> build `$ mvn clean install`

Receiver1 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Receiver2 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

output: only one of QueueingConsumer receivers receives the message
```
my message
```

### DefaultConsumer

#### Permanent

Receiver

> `cd defaultconsumer-handledelivery/receiver-pubsub-permanent-queue/`

> build `$ mvn clean install`

Receiver1 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Receiver2 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

Receiver1
output:
```
my message
```

Receiver2
output:
```
my message
```

#### Temporary

Receiver

> `cd defaultconsumer-handledelivery/receiver-pubsub-temporary-queue/`

> build `$ mvn clean install`

Receiver1 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Receiver2 - on its own terminal

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

Receiver1
output:
```
my message
```

Receiver2
output:
```
my message
```
