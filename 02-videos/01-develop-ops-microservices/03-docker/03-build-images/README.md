# Building Images

`microservices` is the working dir

1, create a docker file `Dockerfile`
```
FROM alpine
MAINTAINER pwillhan
RUN apk update
```

2, build docker image from a docker file
```
$ docker build .
Successfully built 573b82c47404
```

3, run the docker image
```
$ docker run 573b82c47404 uname -a
```

4, add app.js to the current dir
```
var http = require('http');
http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello World\n');
}).listen(3000);
console.log('Server running on port 3000');
```

5, update the docker file
```
FROM alpine
MAINTAINER rickfast
RUN apk update
RUN apk add nodejs
ADD app.js /
EXPOSE 3000
ENTRYPOINT ["node","app.js"]
```

6, $ docker build .

Successfully built e305b9fdeb04

7, $ docker run -p 3000:3000 e305b9fdeb04
1dc255ac9b0c01141f3ee6e1eafa9d22d40623fdc345c567f8c1bc511238aa58

8, go to http://docker:3000/
Hello World

9, $ docker stop 1dc255ac9b0c01141f3ee6e1eafa9d22d40623fdc345c567f8c1bc511238aa58

