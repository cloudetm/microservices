# Docker - ubuntu

https://docs.docker.com/engine/installation/linux/ubuntu/

## Install using the repository

> run as root

```
`CTRL + ALT + T` to open terminal
$ sudo -i
```

> Set up the repository - docker ce

```
# apt-get update
# apt-get install linux-image-extra-$(uname -r) linux-image-extra-virtual
# apt-get install apt-transport-https ca-certificates curl software-properties-common
# curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# apt-key fingerprint 0EBFCD88
# add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
```

> Install Docker

```
# apt-get update
# apt-get install docker-ce
```

> Docker run

```
# docker run hello-world
# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              48b5124b2768        3 months ago        1.84 kB
```
