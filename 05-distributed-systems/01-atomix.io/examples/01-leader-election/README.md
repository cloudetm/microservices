# loader election

## run

```
java -jar target/app-1.0-SNAPSHOT.jar logs/server1 localhost:5000 localhost:5001 localhost:5002
java -jar target/app-1.0-SNAPSHOT.jar logs/server2 localhost:5001 localhost:5000 localhost:5002
java -jar target/app-1.0-SNAPSHOT.jar logs/server3 localhost:5002 localhost:5000 localhost:5001
```
