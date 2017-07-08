# Cassandra

## Installation

http://www.planetcassandra.org/cassandra/

## Start cassandra

```
$ cd /Users/whan/datastax-ddc-3.9.0
$ bin/cassandra -f <- start cassandra in foreground
ctrl+c to shutdown

$ bin/cqlsh
cqlsh> show version
[cqlsh 5.0.1 | Cassandra 3.0.6 | CQL spec 3.4.0 | Native protocol v4]
cqlsh> SOURCE '~/mydir/file.cql' <- executes cql statements 
```

## DevCenter - data management

https://academy.datastax.com/downloads/ops-center

http://www.datastax.com/products/datastax-devcenter-and-development-tools#DataStax-DevCenter

### Launch DevCenter

> `cmd + space`, `DevCenter` <- location `/Users/whan/cassandra/DevCenter`

```
hosts: localhost
port: 9042
```
