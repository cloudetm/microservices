# Setting up a Kafka Cluster

```
download kafka from http://kafka.apache.org/downloads.html
$ mkdir kafka
$ cd kafka/
$ cp ~/Downloads/kafka_2.11-0.9.0.1.tgz .
$ tar xzf kafka_2.11-0.9.0.1.tgz <- unzip
$ mkdir log-1
$ mkdir log-2
$ cd kafka_2.11-0.9.0.1
$ vi config/server.properties
port=9092
log.dirs=~/kafka/log-1
$ bin/zookeeper-server-start.sh config/zookeeper.properties & <- start zoopkeeper in background
$ bin/kafka-server-start.sh config/server.properties & <- start log-1 in background
$ cp config/server.properties config/server2.properties
$ vi config/server2.properties
port=9091
log.dirs=~/kafka/log-2
$ bin/kafka-server-start.sh config/server2.properties & <- start log-2 in background
$ ps -ef | grep kafka <- see all kafka server running
$ bin/kafka-topics.sh
$ bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic first --partitions 2 --replication-factor 2 <- zookeeper window
$ bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic first <- zookeeper window
$ bin/kafka-console-producer.sh --broker-list localhost:9092 --topic first <- producer window
$ bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first <- consumer window
$ bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic first --from-beginning <- consumer window
```

note:
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
