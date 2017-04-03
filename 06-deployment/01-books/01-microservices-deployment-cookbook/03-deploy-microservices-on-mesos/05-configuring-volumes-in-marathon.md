# Configuring volumes in Marathon

##Persist data

> Create directory

```
mkdir -p /opt/packt/geolocation/data
chmod -R 777 /opt
```

> Add `GeoLocationRepository.java`

> Modify `GeoLocationServiceImpl.java` to use `GeoLocationRepository.java`

> Test

```
$ curl -H "Content-Type: application/json" -X POST -d'{"timestamp": 1468203975, "userId": "f1196aac-470e-11e6-beb8-9e71128cae77", "latitude": 41.803488, "longitude": -88.144040}' http://localhost:8080/geolocation
$ ls /opt/packt/geolocation/data/
userf1196aac-470e-11e6-beb8-9e71128cae77_t1468203975
```

##Docker

> Modify Dockerfile

```
RUN mkdir -p /opt/packt/geolocation/data
RUN chmod 777 /opt
```

docker ps -a
docker exec 95aa413a643d ls /opt/packt/geolocation/data
