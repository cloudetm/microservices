# Exchange direct permanent queue

Sender will create exchange and permanent queue, published messages will stay in the queue for consumers

## Run Apps

### Sender

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar` - results: exchange and queue are created, and published message is
sitting in the queue waiting for consumers

### Receiver

> build `$ mvn clean install`

> run `$ java -jar target/app-1.0-SNAPSHOT.jar`

output:
```
 [x] Received 'my message'
```

