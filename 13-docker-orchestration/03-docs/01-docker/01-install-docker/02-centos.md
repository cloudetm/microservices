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
# yum install docker-ce
```

> Start Docker

```
# systemctl start docker
```

> Docker run

```
# docker run hello-world
```
