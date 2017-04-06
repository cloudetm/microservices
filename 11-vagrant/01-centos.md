# vagrant - centos

## installation - virtualbox and vagrant

https://www.youtube.com/watch?v=RhhF8Yh7OnE

### Search Vagrant Boxes

https://atlas.hashicorp.com/boxes/search

### create and destroy vm - centos

https://www.vagrantup.com/intro/getting-started/index.html

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

> run as root

```
[vagrant@localhost ~]$ su root
Password: vagrant
```
