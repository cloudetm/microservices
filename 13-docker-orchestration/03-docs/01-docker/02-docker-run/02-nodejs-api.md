# nodejs api

https://nodejs.org/en/docs/guides/nodejs-docker-webapp/

> vagrant

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

> Start Docker

```
# systemctl start docker
```

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
const PORT = 8080;

// App
const app = express();
app.get('/', function (req, res) {
  res.send('Hello world\n');
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

EXPOSE 8080
CMD [ "npm", "start" ]
```

> Build docker image

```
# docker build -t myservice .

# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myservice           latest              ad251e9978da        13 minutes ago      664MB
node                boron               22fb755e42f6        10 days ago         661MB
```

> Run docker container

`-p` expose ports: host-port=49160, container-port=8080

```
# docker run -p 49160:8080 -d myservice

# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                     NAMES
840816e905bf        myservice           "npm start"         14 minutes ago      Up 14 minutes       0.0.0.0:49160->8080/tcp   reverent_aryabhata

# docker logs 840816e905bf
```

> Test

```
# curl http://localhost:49160
Hello world
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
