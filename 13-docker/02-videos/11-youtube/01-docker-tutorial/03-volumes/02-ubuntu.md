# youtube

https://www.youtube.com/watch?v=rlK1JYsM6Aw

## references

https://docs.docker.com/engine/tutorials/dockervolumes/

https://docs.docker.com/engine/userguide/storagedriver/imagesandcontainers/

## vagrant ubuntu

> Launch vagrant ubuntu

```
$ cd vagrant
$ vagrant up
$ sudo -i # run as root
```

> Setup

```
root@ubuntu-xenial:~# nano myimage.dockerfile
root@ubuntu-xenial:~# cat myimage.dockerfile 
FROM ubuntu:14.04

VOLUME ["/john2"]

ADD file1 /john2/file1

CMD ["/bin/sh"]
root@ubuntu-xenial:~# nano file1
root@ubuntu-xenial:~# cat file1
file_1
```

> Create image has volume

```
root@ubuntu-xenial:~# docker rmi -f myimage # optional
Untagged: myimage:latest
Deleted: sha256:7e72453518c47ab12001a73c377fb514945abf7513c1657f1335e6d4937485ee

root@ubuntu-xenial:~# docker build -f myimage.dockerfile -t myimage .
# docker images  (show my image)
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
myimage                      latest              7e72453518c4        12 minutes ago      188 MB
root@ubuntu-xenial:~# docker inspect myimage
root@ubuntu-xenial:~# docker history myimage
IMAGE               CREATED             CREATED BY                                      SIZE                COMMENT
00f017a8c2a6        3 weeks ago         /bin/sh -c #(nop)  CMD ["sh"]                   0 B                 
<missing>           3 weeks ago         /bin/sh -c #(nop) ADD file:c9ecd8ff00c653fb65   1.11 MB          
root@ubuntu-xenial:~# docker run -it myimage
# ls  
bin  boot  dev	etc  home  john2  lib  lib64  media  mnt  opt  proc  root  run	sbin  srv  sys	tmp  usr  var
# cat john2/file1
file_1
# exit
```

> Add second volume `john1` to the image already has volume

```
root@ubuntu-xenial:~# docker run -it -v /john1 myimage
# ls
bin  boot  dev	etc  home  john1  john2  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

> Access to docker host directory - mount docker image volume to docker host directory

keep updating configuration in host without updating container

```
$ mkdir /tmp/john3
$ touch /tmp/john3/file3
$ touch /tmp/john3/file4
$ ls /tmp/john3/
file3	file4
$ docker run -it -v /tmp/john3:/john3 myimage
# ls
bin  boot  dev	etc  home  john2  john3  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
# cd john3
# ls
# touch file5
$ docker run -it -v /tmp/john3:/john3:ro myimage
# cd john3
# ls
file5
# touch file6
touch: cannot touch 'file6': Read-only file system
```

put file `~/.bash_history` into container

```
$ docker run -it -v ~/.bash_history:/.bash_history myimage
# ls -a 
.   .bash_history  bin	 dev  home   lib    media  opt	 root  sbin  sys  usr
..  .dockerenv	   boot  etc  john2  lib64  mnt    proc  run   srv   tmp  var
# cat .bash_history
```

> Remove all containers

```
docker ps -a   (see what's running)
docker kill $(docker ps -q)
docker rm $(docker ps -aq)   
docker ps -a
```

> Share containers

```
$ docker run -it --name will1 -v /will1 myimage # john2 that comes from myimage, and will1
# ls
bin  boot  dev	etc  home  john2  lib  lib64  media  mnt  opt  proc  root  run	sbin  srv  sys	tmp  usr  var  will1
# ctrl-p-q (keep running)
$ docker run -it --name will2 --volumes-from will1 myimage # john2 that comes from myimage, and will1 that comes from will1
# ls
bin  boot  dev	etc  home  john2  lib  lib64  media  mnt  opt  proc  root  run	sbin  srv  sys	tmp  usr  var  will1
$ docker run -it --name will3 --volumes-from will2 myimage # john2 that comes from myimage, and will1 that comes from will1
# ls
bin  boot  dev	etc  home  john2  lib  lib64  media  mnt  opt  proc  root  run	sbin  srv  sys	tmp  usr  var  will1
```
