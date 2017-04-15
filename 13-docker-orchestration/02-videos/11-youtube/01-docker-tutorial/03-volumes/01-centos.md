# youtube

https://www.youtube.com/watch?v=rlK1JYsM6Aw

## references

https://docs.docker.com/engine/tutorials/dockervolumes/

https://docs.docker.com/engine/userguide/storagedriver/imagesandcontainers/

## vagrant centos

> Launch vagrant centos

```
$ cd vagrant/centos
$ vagrant up
```

> run as root

```
$ su root
```

> Setup

```
[root@localhost vagrant]# nano myimage.dockerfile
[root@localhost vagrant]# cat myimage.dockerfile 
FROM ubuntu:14.04

VOLUME ["/john2"]

ADD file1 /john2/file1

CMD ["/bin/sh"]
[root@localhost vagrant]# nano file1
[root@localhost vagrant]# cat file1
file_1
```

> Start docker

```
[root@localhost vagrant]# systemctl start docker
```

> Create docker image has volume

```
[root@localhost vagrant]# docker rmi -f myimage # optional
Untagged: myimage:latest
Deleted: sha256:7e72453518c47ab12001a73c377fb514945abf7513c1657f1335e6d4937485ee

[root@localhost vagrant]# docker build -f myimage.dockerfile -t myimage .
# docker images  (show my image)
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
myimage                      latest              7e72453518c4        12 minutes ago      188 MB
[root@localhost vagrant]# docker inspect myimage
[root@localhost vagrant]# docker history myimage
IMAGE               CREATED             CREATED BY                                      SIZE                COMMENT
00f017a8c2a6        3 weeks ago         /bin/sh -c #(nop)  CMD ["sh"]                   0 B                 
<missing>           3 weeks ago         /bin/sh -c #(nop) ADD file:c9ecd8ff00c653fb65   1.11 MB          
[root@localhost vagrant]# docker run -it myimage
# ls  
bin  boot  dev	etc  home  john2  lib  lib64  media  mnt  opt  proc  root  run	sbin  srv  sys	tmp  usr  var
# cat john2/file1
file_1
# exit
```

> Add second volume `john1` to the image already has volume

```
[root@localhost vagrant]# docker run -it -v /john1 myimage
# ls
bin  boot  dev	etc  home  john1  john2  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
```

> Access to docker host directory - mount docker image volume to docker host directory

keep updating configuration in host without updating container

```
[root@localhost vagrant]# mkdir john3
[root@localhost vagrant]# cd john3
[root@localhost john3]# touch file3
[root@localhost john3]# touch file4
[root@localhost john3]# pwd
/home/vagrant/john3
[root@localhost john3]# cd ..
[root@localhost vagrant]# docker run -it -v /home/vagrant/john3:/john3 myimage
# ls
bin   dev  home   john3  lib64	mnt  proc  run	 srv  tmp  var
boot  etc  john2  lib	 media	opt  root  sbin  sys  usr
# ls john3/
file3  file4
# cd john3
# touch file5
# exit
[root@localhost vagrant]# ls john3
file3  file4  file5
```

> mount docker image volume readonly

```
[root@localhost vagrant]# docker run -it -v /home/vagrant/john3:/john3:ro myimage
# ls
bin   dev  home   john3  lib64	mnt  proc  run	 srv  tmp  var
boot  etc  john2  lib	 media	opt  root  sbin  sys  usr
# cd john3
# touch file8
touch: cannot touch 'file8': Read-only file system
# exit
```

> put file `~/.bash_history` into container

```
[root@localhost vagrant]# ls -a
.  ..  .bash_history  .bash_logout  .bash_profile  .bashrc  file1  john3  myimage.dockerfile  .ssh
[root@localhost vagrant]# docker run -it -v /home/vagrant/.bash_history:/.bash_history myimage
# ls -a
.   .bash_history  bin	 dev  home   lib    media  opt	 root  sbin  sys  usr
..  .dockerenv	   boot  etc  john2  lib64  mnt    proc  run   srv   tmp  var
# ctrl-p-q  # keep in running
```

> Remove all containers

```
[root@localhost vagrant]# docker ps -a   (see all containers)
[root@localhost vagrant]# docker ps   (see what's running containers)
[root@localhost vagrant]# docker kill $(docker ps -q)
[root@localhost vagrant]# docker rm $(docker ps -aq)   
[root@localhost vagrant]# docker ps
```

## Share containers

> Create a new docker container name `john1`

```
[root@localhost vagrant]# docker run -it --name john1 -v /john1 myimage
# ls
bin   dev  home   john2  lib64	mnt  proc  run	 srv  tmp  var
boot  etc  john1  lib	 media	opt  root  sbin  sys  usr
# [root@localhost vagrant]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
8cc4a9883ff0        myimage             "/bin/sh"           22 seconds ago      Up 21 seconds                           john1
# ctrl-p-q  # keep in running
```

> Create a new docker container name `john3` from `john1`

```
[root@localhost vagrant]# docker run -it --name john3 --volumes-from john1 myimage
# ls
bin  boot  dev	etc  home  john1  john2  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
# [root@localhost vagrant]# 
[root@localhost vagrant]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
846174c6b465        myimage             "/bin/sh"           30 seconds ago      Up 29 seconds                           john3
8cc4a9883ff0        myimage             "/bin/sh"           4 minutes ago       Up 4 minutes                            john1
# ctrl-p-q  # keep in running
```

