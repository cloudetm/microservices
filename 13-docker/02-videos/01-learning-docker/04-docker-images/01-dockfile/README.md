# Dockerfile

> `docker build .` - build Dockerfile

```
Sending build context to Docker daemon 2.048 kB
Step 1 : FROM alpine
latest: Pulling from library/alpine
d0ca440e8637: Pull complete 
Digest: sha256:f655166f57d91bdfc8b3bc75a20391b7516de9f48ca761249c185fcb022124d2
Status: Downloaded newer image for alpine:latest
 ---> 13e1761bf172
Step 2 : MAINTAINER William <pwillhan@gmail.com>
 ---> Running in 78642978df96
 ---> affac30d6c54
Removing intermediate container 78642978df96
Successfully built affac30d6c54
```

> `docker images` - two images created
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
<none>              <none>              affac30d6c54        8 seconds ago       4.797 MB
alpine              latest              13e1761bf172        2 weeks ago         4.797 MB

> `docker build -t rickfast/average .` - build with tagged name

> `docker run rickfast/average 3 4 5` - run container image with args to `ENTRYPOINT`

> `docker images`

```
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
rickfast/average    latest              b6de603869fc        3 minutes ago       24.55 MB
alpine              latest              13e1761bf172        2 weeks ago         4.797 MB
```
