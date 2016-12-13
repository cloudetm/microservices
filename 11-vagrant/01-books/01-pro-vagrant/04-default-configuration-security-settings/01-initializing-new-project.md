# Initializing a New Project

```
$ mkdir ~/vagrant-pro/chapter04/
$ cd ~/vagrant-pro/chapter04/
$ vagrant init -m ubuntu/trusty32
$ ls
total 8
-rw-r--r--  1 whan  386085923  75 Dec 12 16:25 Vagrantfile
```

- `$ vagrant init -m` - -m flag creates a minimal Vagrantfile 

- `$ vagrant init -f` - -f flag overwrites an existing Vagrantfile file

## Security

Recommend VMs on following Vagrant Cloud

- hashicorp/precise64
- ubuntu/trusty32
- chef/centos-6.5

## Booting the Guest OS

> `$ vagrant box remove ubuntu/trusty32` - optional, remove existing ubuntu/trusty32 box

> `$ vagrant box list` - verify ubuntu/trusty32 box is not present

```
$ vagrant box list
$ vagrant box list | grep ubuntu
```

> `$ vagrant up` - boot guest OS (VM)

> download base oxes manually by using `curl` or `wget`

```
$ curl [URL] -o [FILENAME]
$ wget -O [FILENAME] [URL]
$ curl http://boxes.gajdaw.pl/apache/apache-v1.0.0.box -o apache-v1.0.0.box
$ wget -O apache-v1.0.0.box http://boxes.gajdaw.pl/apache/apache-v1.0.0.box
```

## Default Configuration of a VM

- One forwarded port: Host port 2201 is forwarded to guest port 22

```
==> default: Forwarding ports...
    default: 22 (guest) => 2201 (host) (adapter 1)
```

When you access port number 2201 on the host system, you are redirected to port number 22 inside the guest OS (VM)

Vagrantfile forwarded_port 

```
config.vm.network :forwarded_port, guest: 22, host: 2222, host_ip: "127.0.0.1"
```

- One shared folder: The project folder that contains the Vagrantfile is shared by the host and guest OS (VM)

```
==> default: Mounting shared folders...
    default: /vagrant => /Users/whan/vagrant-pro/chapter04
```

**Host OS**

Create a new file

```
$ cd chapter04
$ echo abc > abc.txt
$ vagrant ssh <- remote into Guest OS
```

**Guest OS**

Verify the same file is there

```
vagrant@vagrant-ubuntu-trusty-32:~$ ls /vagrant/
abc.txt  Vagrantfile
vagrant@vagrant-ubuntu-trusty-32:~$ cat /vagrant/abc.txt 
abc
```

Logout Guest OS

```
vagrant@vagrant-ubuntu-trusty-32:~$ logout
```




