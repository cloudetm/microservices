# Docker Images

## Images On Docker Hub

> go to https://hub.docker.com/_/rabbitmq/

> `docker pull rabbitmq`

> `docker run rabbitmq` and ctrl+c to kill it

> `docker pull rabbitmq:3.6.2-management` - pull a specific tag of image

> `docker run -d -p 15672:15672 rabbitmq:3.6.2-management` - run the container

```
fab0bbf6dc2ed21dd983822e5522840755a50c7e22100b6ca1c935d649d74b4c
```

> go to http://192.168.99.100:15672/ to see the rabbitmq UI

## Building Images

> `docker search alpine` - search alpine light weight linux base image

```
NAME                           DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
alpine                         A minimal Docker image based on Alpine Lin...   950       [OK]       
anapsix/alpine-java            Oracle Java 8 (and 7) with GLIBC 2.23 over...   91                   [OK]
```

> `docker images` - list all images

> `docker pull alpine` - pull alpine image

```
Using default tag: latest
latest: Pulling from library/alpine
d0ca440e8637: Pull complete 
Digest: sha256:f655166f57d91bdfc8b3bc75a20391b7516de9f48ca761249c185fcb022124d2
Status: Downloaded newer image for alpine:latest
```

> `docker images`

```
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
alpine              latest              13e1761bf172        2 weeks ago         4.797 MB
```

> `docker run -i -t alpine /bin/sh` - get into alpine interactive terminal

> in the interactive terminal `apk update`

## Command And Entrypoint


