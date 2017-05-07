# nodejs api - centos docker

https://nodejs.org/en/docs/guides/nodejs-docker-webapp/

## vagrant

> create Vagrantfile

```
$ mkdir centos
$ cd centos

$ vagrant init centos/7
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
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant
```

> run as root

```
$ su root
Password: vagrant
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

## Api project - nodejs 

> Create a directory for the project:

```
$ mkdir dockerrun
$ cd dockerrun
```

> package.json

```
{
  "name": "docker_web_app",
  "version": "1.0.0",
  "description": "Node.js on Docker",
  "author": "First Last <first.last@example.com>",
  "main": "server.js",
  "scripts": {
    "start": "node server.js"
  },
  "dependencies": {
    "express": "^4.13.3"
  }
}
```

> server.js

```
'use strict';

const express = require('express');

// Constants
const PORT = 6000;

// App
const app = express();
app.get('/', function (req, res) {
  res.send('Hello world from nodejs!\n');
});

app.listen(PORT);
console.log('Running on http://localhost:' + PORT);
```

> Dockerfile

```
FROM node:boron

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 6000
CMD [ "npm", "start" ]
```

> Build docker image

```
# docker build -t myservice .

# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myservice           latest              65e2d84de616        10 seconds ago      664MB
node                boron               b851da6cea2a        2 days ago          661MB
```

> Run docker container

`-p` expose ports: host-port=49160, container-port=8080

```
              host:container
# docker run -p 80:6000 -d myservice

# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                  NAMES
da60f09f5b47        myservice           "npm start"         7 seconds ago       Up 6 seconds        0.0.0.0:80->6000/tcp   musing_sinoussi

# docker logs da60f09f5b47
npm info it worked if it ends with ok
npm info using npm@3.10.10
npm info using node@v6.10.3

> docker_web_app@1.0.0 start /usr/src/app
> node server.js

npm info lifecycle docker_web_app@1.0.0~prestart: docker_web_app@1.0.0
npm info lifecycle docker_web_app@1.0.0~start: docker_web_app@1.0.0
Running on http://localhost:6000
```

## Test

> Test from centos

```
# curl http://localhost:80
Hello world from nodejs!
```

> Test from Mac

because of Vagrantfile port forwarding

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

```
http://localhost:8080/
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
