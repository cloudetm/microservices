# publish-subscribe

We can use a fanout exchange and bind any number of queues to that exchange regardless of the binding key.
The Receiver class can be used to bind a specified queue to a fanout exchange and receive messages from it.

## Run Apps

### Receiver

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - waiting

### Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

receiver output:
```
my message
```
