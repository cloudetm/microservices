# vagrant

https://www.vagrantup.com/

## installation - virtualbox and vagrant
https://www.youtube.com/watch?v=RhhF8Yh7OnE

### create and destroy vm - ubuntu uses sudo apt-get-udpate

```
$ mkdir vagrant
$ cd vagrant/
$ vagrant init precise32 http://files.vagrantup.com/precise32.box
$ ls -al
$ less Vagrantfile
$ vagrant up
$ vagrant ssh
~$ exit
$ vagrant destroy
```

#### create vm from http://www.vagrantbox.es/

```
$ rm Vagrantfile <- remove Vagrantfile if it already exists
- find virtualbox has puppet
$ vagrant init wheezy64
$ vagrant box add wheezy64 https://www.dropbox.com/s/23gupgb0xompvkm/Wheezy64.box?dl=1
$ vagrant up
$ vagrant ssh
```

### commands

```
vagrant init
vagarnt up
vagrant ssh
vagrant destroy
vagrant reload
```

## vm

### installation

```
~$ sudo apt-get update
~$ sudo apt-get install git
~$ sudo apt-get install yum
~$ sudo apt-get install vim
```
