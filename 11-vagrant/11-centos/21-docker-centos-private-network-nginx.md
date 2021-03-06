# vagrant private_network - centos nginx on docker

https://docs.docker.com/engine/installation/linux/centos/

https://www.digitalocean.com/community/tutorials/how-to-run-nginx-in-a-docker-container-on-ubuntu-14-04

## vagrant

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

> vagrant up and ssh

```
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
```

> run as root

```
$ su root
Password: 
```

## Docker

> setup.sh - `bash setup.sh`

```
#!/usr/bin/env bash

yum install -y yum-utils
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum-config-manager --enable docker-ce-edge

yum makecache fast
yum install -y docker-ce

systemctl start docker
```

> Set up the repository - docker ce

```
# yum install -y yum-utils
# yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# yum-config-manager --enable docker-ce-edge
```

> Install Docker

```
# yum makecache fast
# yum install -y docker-ce
```

> Start Docker

```
# systemctl start docker
```

> Docker run nginx

https://www.digitalocean.com/community/tutorials/how-to-run-nginx-in-a-docker-container-on-ubuntu-14-04

forwarded-port docker port 80 to centos host port 80

```
# docker run --name docker-nginx -p 80:80 -d nginx
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
