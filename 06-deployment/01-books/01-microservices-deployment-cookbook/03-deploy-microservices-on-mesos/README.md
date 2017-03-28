# Deploying Microservices on Mesos

> Create Mesos cluster

under `01-building-microservices-with-java/geolocation/` directory

```
$ docker-compose -f docker-compose-mesos.yml up
ctrl+c to stop
```

In our case, we will use the public repository, and the name of our image is vikrammurugesan/geolocation. 
Please keep in mind that this is my Docker Hub account, and you should be using the image in your account.

## Mesos

http://192.168.99.100:5050

## Marathon

http://192.168.99.100:8080/

> Create Application

```
1, Under General tab
  ID: geolocation
  CPUs: 1
  Memory: 128
  Disk Space: 1024
  Instances: 1
2, Docker Container tab
  Image: pwillhan/geolocation
  Network: Bridged
3, Click "Create Application"
```

> Modify Port

```
1, Under Ports tab
  Container Port: 8080
  Protocol: tcp
2, Click "Change and deploy configuration"
```
  
```
1, Click top right JSON mode to modify json
  servicePort: 8899
2, Click "Change and deploy configuration"
```

> Get IP and Port

```
1, Click the applicaiton to go into the application instance
2, under Instances, ID column, you will see something like 192.168.99.100:31642
```

> CURL - POST and GET

```
$ curl -H "Content-Type: application/json" -X POST -d '{"timestamp": 1468203975, "userId": "f1196aac-470e-11e6-beb8-9e71128e77", "latitude": 41.803488, "longitude": -88.144040}' http://192.168.99.100:31642/geolocation
$ curl http://192.168.99.100:31642/geolocation
```
