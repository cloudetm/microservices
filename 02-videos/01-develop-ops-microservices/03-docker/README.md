# Docker Basics

`microservices` is the working dir

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

1, create a docker file `Dockerfile`
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
$ docker rm cass <- remove container by name
```

Left console window
```
1, $ docker run -d --name cass spotify/cassandra
3, $ docker stop cass  <- graceful shutdown
4, $ docker restart cass
6, $ docker kill cass <- non-graceful shutdown
7, create a killme.rb "vi killme.rb"
at_exit do
  puts "shutting down!"
end
while true do
  puts "hi"
end
8, create a docker file "vi Dockerfile"
FROM alpine
RUN apk update
RUN apk add ruby
ADD killme.rb /
ENTRYPOINT ruby killme.rb
9, $ docker build .
Successfully built a32901d45aee
11, $ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
fff6a97f0ad4        a32901d45aee        "/bin/sh -c 'ruby kil"   15 seconds ago      Up 14 seconds                           jovial_cray
12, $ docker stop fff6a97f0ad4 <- the right console stop without the app "shutting down!"
13, modify the docker file "vi Dockerfile" <- use exact form, represent ENTRYPOINT as json like - the app will run inside container
FROM alpine
RUN apk update
RUN apk add ruby
ADD killme.rb /
ENTRYPOINT ["ruby", "killme.rb"]
14, docker build .
Successfully built b9119b3b908d
16, $ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
1ec6735b1ddd        b9119b3b908d        "ruby killme.rb"    13 seconds ago      Up 12 seconds                           jovial_stonebraker
17, $ docker stop 1ec6735b1ddd <- the right console stop with the app proper "shutting down!"
```

Right console window
```
2, $ docker logs -f cass
5, $ docker logs -f cass
10, $ docker run a32901d45aee <- keep outputing "hi"
15, $ docker run b9119b3b908d
```

## Debugging Docker

```
$ docker run progrium/consul
$ docker ps <- show only running containers by default.
$ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED              STATUS              PORTS                                                              NAMES
ae5007e3de06        progrium/consul     "/bin/start"        About a minute ago   Up About a minute   53/tcp, 53/udp, 8300-8302/tcp, 8400/tcp, 8500/tcp, 8301-8302/udp   adoring_keller
$ docker ps -a <- show all containers
$ docker logs ae5007e3de06
$ docker logs -f ae5007e3de06
$ docker inspect ae5007e3de06
$ docker inspect --format='{{.NetworkSettings.IPAddress}}' ae5007e3de06
172.17.0.2
$ docker run -i -t ubuntu /bin/bash <- get into container
/# exit
$ docker exec -i -t ae5007e3de06 /bin/bash <- get into container as ssh
bash-4.3# ps
PID   USER     TIME   COMMAND
    1 root       0:00 /bin/consul agent -config-dir=/config
   11 root       0:00 /bin/bash
   17 root       0:00 ps
```