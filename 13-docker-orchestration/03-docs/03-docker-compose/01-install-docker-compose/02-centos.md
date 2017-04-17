> Centos - Install Docker Compose

https://docs.docker.com/compose/install/

```
# curl -L "https://github.com/docker/compose/releases/download/1.11.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# chmod +x /usr/local/bin/docker-compose
# docker-compose --version
```

> Create a directory for the project:

```
$ mkdir composetest
$ cd composetest
```

> Create `app.py` in the project directory

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

> Create `requirements.txt` in the project directory

```
flask
redis
```

> Create `Dockerfile` in the project directory

```
FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install -r requirements.txt
CMD ["python", "app.py"]
```

> Create `docker-compose.yml` in the project directory

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

# curl http://0.0.0.0:5000/
Hello World! I have been seen 1 times.
```
