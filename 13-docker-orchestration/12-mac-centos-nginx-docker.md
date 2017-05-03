# Docker - centos on Mac (host)

https://docs.docker.com/engine/installation/linux/centos/

https://www.digitalocean.com/community/tutorials/how-to-run-nginx-in-a-docker-container-on-ubuntu-14-04

## vagrant

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
$ cd vagrant/centos

$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
```

> run as root

```
$ su root
Password: 
```

## Install using the repository

> Set up the repository - docker ce

```
# yum install -y yum-utils
# yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# yum-config-manager --enable docker-ce-edge
```

> Install Docker

```
# yum makecache fast
# yum install docker-ce
```

> Start Docker

```
# systemctl start docker
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

*Test from vagrant centos vm*

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
