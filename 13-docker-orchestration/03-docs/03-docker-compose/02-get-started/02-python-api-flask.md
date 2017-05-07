# python flask api - centos docker-compose 

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

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World from flask!'

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
```

> requirements.txt

```
flask
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
    ports:
     - "80:5000"
      host:container

> docker-compose up

```
# docker-compose up -d

# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                  NAMES
ad9a1f1dacd7        redis:alpine        "docker-entrypoint..."   7 minutes ago       Up 7 minutes        6379/tcp               dockercompose_redis_1
476d3ae212a0        dockercompose_web   "python app.py"          7 minutes ago       Up 7 minutes        0.0.0.0:80->5000/tcp   dockercompose_web_1
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
