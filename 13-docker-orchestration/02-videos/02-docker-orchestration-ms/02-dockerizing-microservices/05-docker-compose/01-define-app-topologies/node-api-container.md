# node api container

> Install Docker Compose

https://docs.docker.com/compose/install/

```
# curl -L "https://github.com/docker/compose/releases/download/1.11.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# chmod +x /usr/local/bin/docker-compose
# docker-compose --version
```

> docker-compose

```
# cat docker-compose.yml 
webservice:
  build: . 
  ports:
    - "3000:80"
  environment: 
    port: "80"
  volumes:
    - .:/user/src/app
```

> docker-compose run

```
# docker-compose run -p -d webservice
```

> list docker-compose containers

```
# docker-compose ps
          Name              Command    State    Ports   
-------------------------------------------------------
vagrant_webservice_run_2   npm start   Up      3000/tcp 

# docker ps
CONTAINER ID        IMAGE                COMMAND             CREATED             STATUS              PORTS               NAMES
dd9ee47afdfc        vagrant_webservice   "npm start"         8 minutes ago       Up 8 minutes        3000/tcp            vagrant_webservice_run_3
```

> docker stop container

```
# docker stop vagrant_webservice_run_2
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
