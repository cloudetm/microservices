# python api - Docker run

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

> app.py

```
from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

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
# docker run -p 5000:5000 -d myservice

# docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS                    NAMES
1dbb56e992c1        myservice           "python app.py"     3 seconds ago       Up 2 seconds        0.0.0.0:5000->5000/tcp   gracious_feynman
# docker logs 1dbb56e992c1
 * Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 711-574-398
```

> Test

```
# curl http://localhost:5000
Hello World!
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
