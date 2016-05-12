# Work queues

https://www.rabbitmq.com/tutorials/tutorial-two-java.html

## Run Apps

### Receiver

> build `$ mvn clean install`

> open two terminals for receivers

> receiver1 run `$ java -jar target/app-1.0-SNAPSHOT.jar`

receiver1 output:
```
 [*] Waiting for messages. To exit press CTRL+C
```

> receiver2 run `$ java -jar target/app-1.0-SNAPSHOT.jar`

receiver2 output:
```
 [*] Waiting for messages. To exit press CTRL+C
```

### Sender

> build `$ mvn clean install`

> run following

```
java -jar target/app-1.0-SNAPSHOT.jar First message.
java -jar target/app-1.0-SNAPSHOT.jar Second message..
java -jar target/app-1.0-SNAPSHOT.jar Third message...
java -jar target/app-1.0-SNAPSHOT.jar Fourth message....
java -jar target/app-1.0-SNAPSHOT.jar Fifth message.....
```

sender output:
```
LMDV-WHAN:sender whan$ java -jar target/app-1.0-SNAPSHOT.jar First message.
 [x] Sent 'First message.'
LMDV-WHAN:sender whan$ java -jar target/app-1.0-SNAPSHOT.jar Second message..
 [x] Sent 'Second message..'
LMDV-WHAN:sender whan$ java -jar target/app-1.0-SNAPSHOT.jar Third message...
 [x] Sent 'Third message...'
LMDV-WHAN:sender whan$ java -jar target/app-1.0-SNAPSHOT.jar Fourth message....
 [x] Sent 'Fourth message....'
LMDV-WHAN:sender whan$ java -jar target/app-1.0-SNAPSHOT.jar Fifth message.....
 [x] Sent 'Fifth message.....'
```

receiver1 output:
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'Second message..'
 [x] Done
 [x] Received 'Fourth message....'
 [x] Done
```

receiver2 output:
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'First message.'
 [x] Done
 [x] Received 'Third message...'
 [x] Done
 [x] Received 'Fifth message.....'
 [x] Done
```
