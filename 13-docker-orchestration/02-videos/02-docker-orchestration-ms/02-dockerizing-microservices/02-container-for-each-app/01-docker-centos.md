# Docker centos example

> Start Docker

```
$ cd vagrant/centos/
$ vagrant up
$ vagrant ssh
$ su root
Password: vagrant
[root@sqlsansby05 vagrant]# systemctl start docker
```

> Install node

```
[root@sqlsansby05 vagrant]# docker run node
[root@sqlsansby05 vagrant]# docker run -it node
> console.log('hello');
hello
ctrl+c, ctrl+c # exit
```

## Build node rest api docker image

> Create server.js, package.json, Dockerfile

```
[root@localhost vagrant]# ls
Dockerfile  package.json  server.js

[root@localhost vagrant]# cat server.js 
var express = require('express');
var app = express();

app.get('/', function(req, res) {
	res.json({'msg': 'helloworld'})
});

var port = process.env.port || 3000;
app.listen(port, function() {
	console.log('server started');
});

[root@localhost vagrant]# cat package.json 
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
  },  "author": "",
  "license": "ISC"
}

[root@localhost vagrant]# cat Dockerfile 
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
[root@localhost vagrant]# docker build -t myservice .
[root@localhost vagrant]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myservice           latest              28d78733908d        3 minutes ago       669MB
```

> Run and Test Docker Container

```
[root@localhost vagrant]# docker run -P -d myservice
7dffafe848c74d10fe6afbf72b28ef3a0106ee8458806a65240595aa369b0154
[root@localhost vagrant]# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                     NAMES
7dffafe848c7        myservice           "npm start"         5 seconds ago       Up 4 seconds        0.0.0.0:32769->3000/tcp   awesome_dijkstra

[root@localhost vagrant]# curl http://localhost:32769
{"msg":"helloworld"}
```
