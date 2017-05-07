# python flask api - centos docker

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

## Api project - python

> Create a directory for the project:

```
$ mkdir dockerrun
$ cd dockerrun
```

> app.py

```
from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World from python flask!\n'

if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)
```

> Dockerfile

```
FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install flask
CMD ["python", "app.py"]
```

> Build docker image

```
# docker build -t myservice .

# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
myservice           latest              78c6aaa22303        7 seconds ago       82.4MB
python              3.4-alpine          9ac5db25a0ca        6 weeks ago         82.4MB
```

> Run docker container

```
                host:conatiner
# docker run -p 80:5000 -d myservice

# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                    NAMES
df26446925d0        myservice           "python app.py"     8 seconds ago       Up 7 seconds        0.0.0.0:6000->5000/tcp   inspiring_lovelace

# docker logs df26446925d0
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 266-612-081
172.17.0.1 - - [05/May/2017 17:16:01] "GET / HTTP/1.1" 200 -
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

```
http://localhost:8080/
```

## Clean up

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
