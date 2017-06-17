# Docker - ubuntu on Mac (host)

https://docs.docker.com/engine/installation/linux/ubuntu/

https://www.digitalocean.com/community/tutorials/how-to-run-nginx-in-a-docker-container-on-ubuntu-14-04

## vagrant

> create Vagrantfile

```
$ mkdir ubuntu
$ cd ubuntu

$ vagrant init ubuntu/xenial64
```

> `Vagrantfile` - port forwarding changes

https://www.vagrantup.com/docs/networking/basic_usage.html

`host` is Mac

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

> vagrant up and ssh

```
$ cd vagrant

$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
```

> run as root

```
$ sudo -i
```

## Install using the repository

> Set up the repository - docker ce

```
apt-get update
apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual -y
apt-get install apt-transport-https ca-certificates curl software-properties-common -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
apt-key fingerprint 0EBFCD88
add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```

> Install Docker

```
apt-get update
apt-get install docker-ce -y
```

> Docker run nginx

https://www.digitalocean.com/community/tutorials/how-to-run-nginx-in-a-docker-container-on-ubuntu-14-04

```
# docker run --name docker-nginx -p 80:80 -d nginx
```

> Testing

Because of port forwarding

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

*Test from vagrant ubuntu vm*

```
# curl http://localhost
```

*Test from Mac (host)*

```
http://localhost:8080/
```

> List docker images

```
# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
nginx               latest              46102226f2fd        7 days ago          109MB
```

> List running containers

```
# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                NAMES
515de5a7bb04        nginx               "nginx -g 'daemon ..."   12 minutes ago      Up 12 minutes       0.0.0.0:80->80/tcp   docker-nginx
```

> Delete containers and images

```
# Delete all containers
docker rm $(docker ps -a -q)
# Force delete all containers
docker rm -f $(docker ps -a -q)

# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)
```