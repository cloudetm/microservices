# Web Application Images

> `docker run -d -p 6379:6379 --name redis redis` - run a redis container

```
11e66a248dbdf6058c05d9e7af6ea3795d8f4d03e944550822ce18ffb82b2851
```

> `docker ps` - list running containers

```
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
11e66a248dbd        redis               "docker-entrypoint.sh"   51 seconds ago      Up 50 seconds       0.0.0.0:6379->6379/tcp   redis
```

> `docker build -t rickfast/counter .` - build a container image with tag name

> `docker images`

```
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
rickfast/counter    latest              5110a037c015        4 minutes ago       23.8 MB
redis               latest              be9c5a746699        10 days ago         184.9 MB
alpine              latest              13e1761bf172        2 weeks ago         4.797 MB
```

> `docker run -p 4567:4567 --link redis:redis rickfast/counter` - run the container with linked to redis

> go to `http://192.168.99.100:4567/`

```
Hello World!

This page has been viewed 1 times!
```

