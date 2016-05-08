# Publish/Subscribe

https://www.rabbitmq.com/tutorials/tutorial-three-java.html

We're also interested only in currently flowing messages not in the old ones.

Firstly, whenever we connect to Rabbit we need a fresh, empty queue. To do this we could create a queue with a random name, or, even better - let the server choose a random queue name for us.

Secondly, once we disconnect the consumer the queue should be automatically deleted.

## Run Apps

### Receiver

> build `$ mvn package`

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

> build `$ mvn package`

> run following

```
$ java -jar target/app-1.0-SNAPSHOT.jar
```

sender output:
```
 [x] Sent 'info: Hello World!'
```

receiver1 output:
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'info: Hello World!'
```

receiver2 output:
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'info: Hello World!'
```

> `$ rabbitmqctl list_bindings` - there are two random generated queues for `logs` exchange
```
Listing bindings ...
	exchange	amq.gen-19P1n3niq4ZCAy_H6fhmVw	queue	amq.gen-19P1n3niq4ZCAy_H6fhmVw	[]
	exchange	amq.gen-j254QwyLx5kAFMVlf9RCvw	queue	amq.gen-j254QwyLx5kAFMVlf9RCvw	[]
```
