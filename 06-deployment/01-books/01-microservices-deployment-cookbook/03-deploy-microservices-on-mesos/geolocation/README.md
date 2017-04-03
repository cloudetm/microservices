# Web Api

**Add maven-shade-plugin to pom.xml**

```
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <createDependencyReducedPom>true</createDependencyReducedPom>
          <filters>
            <filter>
              <artifact>*:*</artifact>
              <excludes>
                <exclude>META-INF/*.SF</exclude>
                <exclude>META-INF/*.DSA</exclude>
                <exclude>META-INF/*.RSA</exclude>
              </excludes>
            </filter>
          </filters>
        </configuration>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer
                        implementation="org.apache.maven.plugins.shade.resource.ServicesResourceTransformer" />
                <transformer
                        implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <mainClass>com.company.app.App</mainClass>
                </transformer>
              </transformers>
            </configuration>
          </execution>
        </executions>
      </plugin>
```

## Run the App - Command Line

> `$ mvn clean package` builds the fat JAR

> `$ java -jar target/geolocation-1.0-SNAPSHOT.jar` launch the app

## Run the App - Intellij

### Server

Launch App with Program args: server

### Client - cURL

**POST**

> curl -H "Content-Type: application/json" -X POST -d '{"timestamp": 1468203975, "userId": "f1196aac-470e-11e6-beb8-9e71128cae77", "latitude": 41.803488, "longitude": -88.144040}' http://localhost:8080/geolocation

{"latitude":41.803488,"longitude":-88.14404,"userId":"f1196aac-470e-11e6-beb8-9e71128cae77","timestamp":1468203975}

**GET**

> curl http://localhost:8080/geolocation

[{"latitude":41.803488,"longitude":-88.14404,"userId":"f1196aac-470e-11e6-beb8-9e71128cae77","timestamp":1468203975}]

**GET All**

> `http://localhost:8080/contacts`

```
[{"id":0,"name":"Tom"},{"id":1,"name":"Dick"},{"id":2,"name":"Harry"}]
```

**GET**

> `$ curl http://localhost:8080/contacts/2`

```
{"id":1,"name":"Dick"}
```

**POST**

> `$ curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "name": "Will"}' "http://localhost:8080/contacts"`

**PUT**

> `curl -X PUT -H "Content-Type: application/json" -d '{"id": 1, "name": "Will"}' "http://localhost:8080/contacts/1"`

```
{"id":0,"name":"Will"}
```

**DELETE**

> `curl -X DELETE "http://localhost:8080/contacts/4"`
