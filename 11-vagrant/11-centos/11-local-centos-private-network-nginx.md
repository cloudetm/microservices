# vagrant private_network nginx web server - centos

https://www.youtube.com/watch?v=PmOMc4zfCSw

> create Vagrantfile

```
$ mkdir centos
$ cd centos

$ vagrant init centos/7
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

```
$ su root
Password: vagrant
```

> install nginx

https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-centos-7

```
yum install -y epel-release
yum install -y nginx
```

> start nginx

```
systemctl start nginx
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

> create domain name - mac

1, $ atom /etc/hosts
2, add following to end of the `hosts` file
```
55.55.55.5 myawesomesite.com
```
3, save it. may need to enter password
4, go to http://myawesomesite.com/ - result: `Welcome to nginx!`
