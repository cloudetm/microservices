# Docker Basics

https://docs.docker.com/engine/installation/mac/

```
$ docker-machine env default
```

## Installing Docker

https://www.docker.com/products/docker-toolbox

Docker Terminal

Run following commands after installed docker terminal
```
1, launch `docker terminal`
docker is configured to use the default machine with IP 192.168.99.100
2, $ docker-machine ls
3, $ docker run hello-world
```

Kitematic
```
1, lanuch kitematic
2, create redis image
```

hosts file

add docker as `DOCKER_HOST`
```
$ atom /etc/hosts
192.168.99.100 docker
```

## Running Containers

```
1, $ docker images <- check local images
2, go to https://hub.docker.com/ to find images
3, search for `consul` and use progrium/consul at https://hub.docker.com/r/progrium/consul/
4, $ docker pull progrium/consul
5, $ docker run -p 8500:8500 progrium/consul -server -bootstrap -ui-dir ui
6, go to http://docker:8500
7, $ docker run -d -p 8500:8500 progrium/consul -server -bootstrap -ui-dir ui <- run container in background
b09a8b363c7f6a0bec5ab5f7f87aca4697f48e4d84ad7c0cba59a32ec8796c2d
8, $ docker ps <- show running containers
9, $ docker stop b09a8b363c7f6a0bec5ab5f7f87aca4697f48e4d84ad7c0cba59a32ec8796c2d
```

## Building Images

1, create a docker file
```
FROM alpine
MAINTAINER pwillhan
RUN apk update
```

2, build docker image from a docker file
```
$ docker build .
Successfully built 573b82c47404
```

3, run the docker image
```
$ docker run 573b82c47404 uname -a
```

4, add app.js to the current dir
```
var http = require('http');
http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello World\n');
}).listen(3000);
console.log('Server running on port 3000');
```

5, update the docker file
```
FROM alpine
MAINTAINER rickfast
RUN apk update
RUN apk add nodejs
ADD app.js /
EXPOSE 3000
ENTRYPOINT ["node","app.js"]
```

6, $ docker build .

Successfully built e305b9fdeb04

7, $ docker run -p 3000:3000 e305b9fdeb04
1dc255ac9b0c01141f3ee6e1eafa9d22d40623fdc345c567f8c1bc511238aa58

8, go to http://docker:3000/
Hello World

9, $ docker stop 1dc255ac9b0c01141f3ee6e1eafa9d22d40623fdc345c567f8c1bc511238aa58

## Publishing Images

1, create a docker file
```
FROM ubuntu
MAINTAINER pwillhan
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
```

2, $ docker build .

Successfully built 2f4c2799b0c0

3, $ docker run 2f4c2799b0c0 java -version

4, $ docker tag 2f4c2799b0c0 pwillhan/microservice-java-base

5, $ docker login

6, $ docker push pwillhan/microservice-java-base

7, go to https://hub.docker.com/r/pwillhan/microservice-java-base/

## Container Lifecycle

```
$ docker ps -a <- show all containers
$ docker rm cass <- remove container named
```

Left console
```
1, $ docker run -d --name cass spotify/cassandra
3, $ docker stop cass
```

Right console
```
2, $ docker logs -f cass
```

