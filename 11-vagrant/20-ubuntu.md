# vagrant - ubuntu

## installation - virtualbox and vagrant

https://www.youtube.com/watch?v=RhhF8Yh7OnE

### Search Vagrant Boxes

https://atlas.hashicorp.com/boxes/search

### create and destroy vm - ubuntu uses sudo apt-get-udpate

https://www.vagrantup.com/intro/getting-started/index.html

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
$ lsb_release -a # version
```

> run as root

$ sudo -i

### ubuntu command

```
~$ sudo apt-get update
~$ sudo apt-get install git
~$ sudo apt-get install yum
~$ sudo apt-get install vim
```
