# Docker - centos

https://docs.docker.com/engine/installation/linux/centos/

## Install using the repository

> run as root

```
$ su root
Password: 
```

> SET UP THE REPOSITORY - docker ce

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

# docker run -it -v /john1 busybox
/ # ls
bin    dev    etc    home   john1  proc   root   sys    tmp    usr    var
/ # 
ctrl-p-q  # keep in running
# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
busybox             latest              00f017a8c2a6        3 weeks ago         1.11 MB
```
