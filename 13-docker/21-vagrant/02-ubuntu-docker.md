# vagrant - ubuntu docker

## Install virtualbox and vagrant

https://www.youtube.com/watch?v=RhhF8Yh7OnE

### Search Vagrant Boxes

https://atlas.hashicorp.com/boxes/search

### Create and destroy vm - ubuntu uses sudo apt-get-udpate

https://www.vagrantup.com/intro/getting-started/index.html

https://www.youtube.com/watch?v=wPmvN7KcOlI

```
$ mkdir vagrant
$ cd vagrant/

$ vagrant init hashicorp/precise64 # DO NOT because old version 12.04

Search new version Xenial 16.04 at https://atlas.hashicorp.com/boxes/search 
https://atlas.hashicorp.com/ubuntu/boxes/xenial64

$ vagrant init ubuntu/xenial64
$ ls -al
$ less Vagrantfile
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
~$ exit
$ vagrant destroy
```

> ubuntu version

$ lsb_release -a

## Docker

https://docs.docker.com/engine/installation/linux/ubuntu/

### Install using the repository

> SET UP THE REPOSITORY - docker ce

```
$ sudo apt-get update
$ sudo apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```

> INSTALL DOCKER

```
$ sudo apt-get update
$ sudo apt-get install docker-ce
$ sudo docker run hello-world # test
```

> run as root

$ sudo -i # run as root

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
