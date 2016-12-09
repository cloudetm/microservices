# Debugging Docker

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
