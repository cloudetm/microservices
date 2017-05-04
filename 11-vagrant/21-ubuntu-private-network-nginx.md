# vagrant private_network nginx web server - ubuntu

https://www.youtube.com/watch?v=PmOMc4zfCSw

> create Vagrantfile

```
$ mkdir foo
$ cd foo

$ vagrant init ubuntu/xenial64
```

> add `private_network` to Vagrantfile

1, open `Vagrantfile` under `vagrant` folder

2, uncomment following line and change the ip to "55.55.55.5"

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "private_network", ip: "55.55.55.5"
end
```

> launch centos vm and ssh into it

```
$ vagrant up
$ vagrant ssh
```

> run as root

$ sudo -i

> install nginx

https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-16-041

```
$ apt-get update
$ apt-get install nginx
```

> Testing

Because of private_network below

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "private_network", ip: "55.55.55.5"
end
```

*Test from vagrant centos vm*

```
# curl http://55.55.55.5/
```

*Test from Mac (host)*

```
http://55.55.55.5/
```
