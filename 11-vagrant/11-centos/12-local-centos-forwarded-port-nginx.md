# vagrant forwarded_port nginx web server - centos

> create Vagrantfile

```
$ mkdir centos
$ cd centos

$ vagrant init centos/7
```

> `Vagrantfile` - port forwarding changes

https://www.vagrantup.com/docs/networking/basic_usage.html

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

> vagrant up and ssh

```
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
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

Because of port forwarding

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

*Test from vagrant centos vm*

```
# curl http://localhost
```

*Test from Mac (host)*

```
http://localhost:8080/
```
