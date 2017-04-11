# vagrant - centos docker

## Install virtualbox and vagrant

https://www.youtube.com/watch?v=RhhF8Yh7OnE

### Search Vagrant Boxes

https://atlas.hashicorp.com/boxes/search

### Create and destroy vm - centos

https://www.vagrantup.com/intro/getting-started/index.html

https://www.youtube.com/watch?v=wPmvN7KcOlI

```
$ mkdir vagrant
$ cd vagrant/centos

Search centos at https://atlas.hashicorp.com/boxes/search 
https://atlas.hashicorp.com/centos/boxes/7

$ vagrant init centos/7
$ ls -al
$ less Vagrantfile
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
~$ exit
$ vagrant destroy
```

> centos version

$ hostnamectl 

## Docker

https://docs.docker.com/engine/installation/linux/centos/

### Install using the repository

> SET UP THE REPOSITORY - docker ce

```
$ sudo yum install -y yum-utils
$ sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
$ sudo yum-config-manager --enable docker-ce-edge
```

> INSTALL DOCKER

```
$ sudo yum makecache fast
$ sudo yum install docker-ce
```

> Start docker

```
$ sudo systemctl start docker
```

> run as root

```
[vagrant@localhost ~]$ su root
Password: vagrant
[root@localhost vagrant]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              48b5124b2768        2 months ago        1.84kB
```

> docker run

```
# docker run -it -v /john1 busybox
/ # ls
bin    dev    etc    home   john1  proc   root   sys    tmp    usr    var
/ # 
ctrl-p-q  # keep in running
# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
busybox             latest              00f017a8c2a6        3 weeks ago         1.11 MB
```

> Reboot

```
[root@localhost vagrant]# reboot
```
