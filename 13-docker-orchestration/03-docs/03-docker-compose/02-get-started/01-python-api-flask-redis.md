# python api - Get started with Docker Compose

https://docs.docker.com/compose/gettingstarted/

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
$ mkdir dockercompose
$ cd dockercompose
```

> app.py

```
from flask import Flask
from redis import Redis

app = Flask(__name__)
redis = Redis(host='redis', port=6379)

@app.route('/')
def hello():
    count = redis.incr('hits')
    return 'Hello World! I have been seen {} times.\n'.format(count)

if __name__ == "__main__":
    app.run(host="0.0.0.0", debug=True)
```

> requirements.txt

```
flask
redis
```

> Dockerfile

```
FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD ["python", "app.py"]
```

> docker-compose.yml

```
version: '2'
services:
  web:
    build: .
    ports:
     - "5000:5000"
    volumes:
     - .:/code
  redis:
    image: "redis:alpine"
```

> docker-compose up

```
# docker-compose up -d

# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
e8ebff4febca        redis:alpine        "docker-entrypoint..."   8 seconds ago       Up 6 seconds        6379/tcp                 composetest_redis_1
297121b42d99        composetest_web     "python app.py"          8 seconds ago       Up 6 seconds        0.0.0.0:5000->5000/tcp   composetest_web_1
```

> Test

```
# curl http://0.0.0.0:5000/
Hello World! I have been seen 1 times.
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
