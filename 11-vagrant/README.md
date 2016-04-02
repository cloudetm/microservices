# vagrant

## installation - virtualbox and vagrant
https://www.youtube.com/watch?v=RhhF8Yh7OnE

### create and destroy vm - CentOS

$ mkdir vagrant
$ cd vagrant/
$ vagrant init precise32 http://files.vagrantup.com/precise32.box
$ ls -al
$ less Vagrantfile
$ vagrant up
$ vagrant ssh
vagrant@precise32:~$ exit
$ vagrant destroy

### commands

vagrant init
vagarnt up
vagrant ssh
vagrant destroy
vagrant reload

## vm

### installation

~$ sudo apt-get install git
~$ sudo apt-get install yum
~$ sudo apt-get install vim
