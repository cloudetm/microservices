# Routing

https://www.rabbitmq.com/tutorials/tutorial-four-java.html

We want to extend that to allow filtering messages based on their severity. For example we may want a program which
writes log messages to the disk to only receive critical errors, and not waste disk space on warning or info log messages.

We will use a direct exchange instead. The routing algorithm behind a direct exchange is simple - a message goes to the
queues whose binding key exactly matches the routing key of the message.

It is perfectly legal to bind multiple queues with the same binding key. In our example we could add a binding between
X and Q1 and Q2 with binding key black. In that case, the direct exchange will behave like fanout and will broadcast
the message to all the matching queues. A message with routing key black will be delivered to both Q1 and Q2.

## Run Apps

### Receiver

> build `$ mvn clean install`

> open two terminals for receivers

> receiver1 run `$ java -jar target/app-1.0-SNAPSHOT.jar error` - routing key: `error`

receiver1 output: receive error
```
 [*] Waiting for messages. To exit press CTRL+C
```

> receiver2 run `$ java -jar target/app-1.0-SNAPSHOT.jar info warning error` - routing keys: `info`, `warning`, `error`

receiver2 output: receive info, warning, error
```
 [*] Waiting for messages. To exit press CTRL+C
```

### Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar error "ERROR occur"`

sender output:
```
 [x] Sent 'error':'ERROR occur'
```

receiver1 output: receive error
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Sent 'error':'ERROR occur'
```

receiver2 output: receive info, warning, error
```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Sent 'error':'ERROR occur'
```

> `$ rabbitmqctl list_bindings` - there are two random generated queues for `logs` exchange
```
Listing bindings ...
	exchange	amq.gen-H3jlBDL0QrYjJ52BlHBGPA	queue	amq.gen-H3jlBDL0QrYjJ52BlHBGPA[]
	exchange	amq.gen-aAaIrpe9jIzcAPCPDyixLg	queue	amq.gen-aAaIrpe9jIzcAPCPDyixLg[]
```
