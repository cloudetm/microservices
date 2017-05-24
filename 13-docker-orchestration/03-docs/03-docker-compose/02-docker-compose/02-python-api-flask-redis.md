# python flask api - Get started with Docker Compose

https://docs.docker.com/compose/gettingstarted/

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

`guest` is centos

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

curl -L https://github.com/docker/compose/releases/download/1.12.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

systemctl start docker

mkdir dockercompose
cd dockercompose # cd does not work here
```

> Set up the repository - docker ce

```
yum install -y yum-utils
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum-config-manager --enable docker-ce-edge
```

> Install Docker

```
yum makecache fast
yum install -y docker-ce
```

> Install docker-compose

```
curl -L https://github.com/docker/compose/releases/download/1.12.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

> Start Docker

```
systemctl start docker
```

> Create a directory for the project:

```
mkdir dockercompose
cd dockercompose
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
    return 'Hello World from flask! I have been seen {} times.\n'.format(count)

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
     - "80:5000"
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

## Test

> Test from centos

```
# curl http://0.0.0.0:80
or
# curl http://localhost:80
Hello World from python flask!
```

> Test from Mac

because of Vagrantfile port forwarding

```
Vagrant.configure("2") do |config|
  # ...
  config.vm.network "forwarded_port", guest: 80, host: 8080
end
```

go to http://localhost:8080/

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
