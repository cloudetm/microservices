# Docker node api example

## Docker run

> Pull node docker image

```
# docker run node
# docker run -it node
> console.log('hello');
hello
ctrl+c, ctrl+c # exit
```

## Build node rest api docker image

> Create server.js, package.json, Dockerfile

```
# ls
Dockerfile  package.json  server.js

# cat server.js 
var express = require('express');
var app = express();

app.get('/', function(req, res) {
	res.json({'msg': 'helloworld'})
});

var port = process.env.port || 3000;
app.listen(port, function() {
	console.log('server started');
});

# cat package.json 
{
  "name": "2-2",
  "version": "1.0.0",
  "description": "",
  "main": "server.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1",
    "start": "node server.js"
  },
  "dependencies": {
    "express": "^4.13.3"
  },
  "author": "",
  "license": "ISC"
}

# cat Dockerfile 
FROM node:latest

RUN mkdir -p /user/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app
RUN npm install

EXPOSE 3000

CMD [ "npm", "start" ]
```

> Build Docker Image

```
# docker build -t myservice .
# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myservice           latest              28d78733908d        3 minutes ago       669MB
```

> docker run and test docker container

```
# docker run -P -d myservice
7dffafe848c74d10fe6afbf72b28ef3a0106ee8458806a65240595aa369b0154
# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                     NAMES
7dffafe848c7        myservice           "npm start"         5 seconds ago       Up 4 seconds        0.0.0.0:32769->3000/tcp   awesome_dijkstra

# curl http://localhost:32769
{"msg":"helloworld"}
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
