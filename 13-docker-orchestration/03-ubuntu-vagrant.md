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
