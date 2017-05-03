# Centos - Install Docker

https://docs.docker.com/engine/installation/linux/centos/

## Install using the repository

> run as root

```
$ su root
Password: 
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

```
# docker run --name docker-nginx -p 80:80 -d nginx
```

> Test - from vagrant centos vm

```
# curl http://localhost
```
