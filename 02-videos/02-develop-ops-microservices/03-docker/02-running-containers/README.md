# Running Containers

```
1, $ docker images <- check local images
2, go to https://hub.docker.com/ to find images
3, search for `consul` and use progrium/consul at https://hub.docker.com/r/progrium/consul/
4, $ docker pull progrium/consul
5, $ docker run -p 8500:8500 progrium/consul -server -bootstrap -ui-dir ui
6, go to http://docker:8500
7, $ docker run -d -p 8500:8500 progrium/consul -server -bootstrap -ui-dir ui <- run container in background
b09a8b363c7f6a0bec5ab5f7f87aca4697f48e4d84ad7c0cba59a32ec8796c2d
8, $ docker ps <- show running containers
9, $ docker stop b09a8b363c7f6a0bec5ab5f7f87aca4697f48e4d84ad7c0cba59a32ec8796c2d
```
