# Installing Docker

https://www.docker.com/products/docker-toolbox

Docker Terminal

Run following commands after installed docker terminal
```
1, launch `docker terminal`
docker is configured to use the default machine with IP 192.168.99.100
2, $ docker-machine ls
3, $ docker run hello-world
```

Kitematic
```
1, lanuch kitematic
2, create redis image
```

hosts file

add docker as `DOCKER_HOST`
```
$ atom /etc/hosts
192.168.99.100 docker
```
