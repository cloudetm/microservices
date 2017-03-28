# Getting Started

## Docker Toolbox (Mac)

> Install

https://www.docker.com/products/docker-toolbox

> launch `Docker Quickstart Terminal`

```
docker is configured to use the default machine with IP 192.168.99.100
For help getting started, check out the docs at https://docs.docker.com
```

> run `eval $(docker-machine env default)` when you have following error 

```
docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?.
See 'docker run --help'.
```

> `docker run rickfast/hello-oreilly` under docker terminal

```
Hello O'Reilly!
```

> `docker run -p 4567:4567 -d rickfast/hello-oreilly-http` - launch a container and mapp the 4567 to docker host

> `curl 192.168.99.100:4567`

## Docker Machine

> `docker-machine ls`

> `docker-machine create --driver virtualbox rick`

> `docker-machine ls`

```
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER    ERRORS
default   -        virtualbox   Running   tcp://192.168.99.100:2376           v1.10.3   
rick      -        virtualbox   Running   tcp://192.168.99.101:2376           v1.11.1
```

> `eval $(docker-machine env default)` - point to default container

> `docker-machine ls`

```
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER    ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v1.10.3   
rick      -        virtualbox   Running   tcp://192.168.99.101:2376           v1.11.1
```

> `docker ps` - list running container

> `eval $(docker-machine env rick)` - point to default container

> `docker-machine ls`

```
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER    ERRORS
default   -        virtualbox   Running   tcp://192.168.99.100:2376           v1.10.3   
rick      *        virtualbox   Running   tcp://192.168.99.101:2376           v1.11.1
```

> `docker run -p 4567:4567 -d rickfast/hello-oreilly-http` - run the application on the `rick` container

> `docker-machine ip rick` - get ip address

```
192.168.99.101
```

> `curl 192.168.99.101:4567` - ping the endpoint

```
Hello O'Reilly!
```

> `docker ps` - list running container

```
CONTAINER ID        IMAGE                         COMMAND             CREATED             STATUS              PORTS                    NAMES
d15c0aa2b382        rickfast/hello-oreilly-http   "ruby hello.rb"     9 minutes ago       Up 3 minutes        0.0.0.0:4567->4567/tcp   fervent_hawking
```

> `docker-machine stop rick` - stop docker `rick`

> `docker ps` - list running container; no running container

```
Cannot connect to the Docker daemon. Is the docker daemon running on this host?
```

> `docker-machine rm rick` - remove rick

```
About to remove rick
Are you sure? (y/n): y
Successfully removed rick
```

## Docker Hub Setup

> https://hub.docker.com/

```
username: pwillhan
```
