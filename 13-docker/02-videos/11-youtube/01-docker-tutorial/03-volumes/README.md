# Volumes

https://www.youtube.com/watch?v=rlK1JYsM6Aw

## references

https://docs.docker.com/engine/tutorials/dockervolumes/

https://docs.docker.com/engine/userguide/storagedriver/imagesandcontainers/

## Simple Run

### no volume

```
$ docker run -it busybox
/ # ls
bin   dev   etc   home  proc  root  sys   tmp   usr   var
```

### volume 

> `-v /john1`

```
$ docker run -it -v /john1 busybox
/ # ls
bin    dev    etc    home   john1  proc   root   sys    tmp    usr    var
/ # cd john1
/john1 # touch file1
ctrl-p-q  # keep in running
$ docker ps
CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS              PORTS               NAMES
8221467ef485        busybox                     "sh"                     2 minutes ago       Up 2 minutes                            vigilant_lamarr
$ docker restart 8221467ef485 # stop and start the container
8221467ef485
$ docker exec 8221467ef485 ls /john1
file1
```
The file stays until we remove the container

> start a new container with volume `-v /john1`

```
$ docker run -it -v /john1 busybox
/ # cd john1
/john1 # ls
```

`file1` is not there because it is a new container

