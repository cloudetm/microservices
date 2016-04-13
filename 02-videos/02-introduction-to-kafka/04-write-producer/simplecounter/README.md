# Writing a Kafka Producer

> launch consumer console
```
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first <- consumer window
```

> run old producer app
```
output:
Starting...
0
1
2
... and we are done. This took 1709 ms.
```

# Command line
```
vi config/server.properties
add "delete.topic.enable=true" at the end of config/server.properties
bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic first <- delete if it already exists

bin/zookeeper-server-start.sh config/zookeeper.properties &
bin/kafka-server-start.sh config/server.properties &
bin/kafka-server-start.sh config/server2.properties &

bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic first --partitions 2 --replication-factor 2 <- zookeeper window

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic first <- producer window
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first <- consumer window
type "hello" in producer window
should see "hello" in consumer window

ps -ef | grep kafka <- see all kafka server running
bin/kafka-server-stop.sh config/server.properties
bin/kafka-server-stop.sh config/server2.properties
bin/zookeeper-server-stop.sh config/zookeeper.properties

kill -9 1620 <- kill process if stop.sh doesn't work
reboot the machine <- reboot if kill doesn't work
```
