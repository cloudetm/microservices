# Docker commands

> Create Docker Host (Virtual Box VM)

```
docker-machine ls
docker-machine stop default
docker-machine rm default
docker-machine create -d virtualbox --virtualbox-memory=4096 --virtualbox-cpu-count=4 default
docker-machine start default
eval $(docker-machine env default)
```

> Find the IP of the Docker VM

docker-machine ip default

> Build Dockerfile Docker-Container image

```
docker build -t packt/geolocation .
or
docker build -t pwillhan/geolocation .
docker images
```

> Start a container running geolocation - Run Docker-Container image

```
docker run -p 8080:8080 packt/geolocation
curl http://192.168.99.100:8080/contacts
```

> Remove Docker-Container image

```
$ docker rmi packt/geolocation
Untagged: packt/geolocation:latest
```

> List running Docker-Container images

docker ps -a

> Stop Docker-Containers

docker stop $(docker ps -a -q)


> Push Docker-Image to Docker Hub

```
docker tag packt/geolocation pwillhan/geolocation
docker push pwillhan/geolocation
https://hub.docker.com/r/pwillhan/geolocation/
```
