# Docker Swarm - Mac

https://docs.docker.com/swarm/install-w-machine/

## Create a Docker Swarm

> List the machines

```
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL   SWARM   DOCKER    ERRORS
default   -        virtualbox   Stopped                 Unknown   
```

> Create a VirualBox machine called `local`

```
$ docker-machine create -d virtualbox local
```

> Load the `local` machine configuration into your shell

```
$ eval "$(docker-machine env local)"
```

> Generate a discovery token using the Docker Swarm image

The token is at the end `d9b20ca5682d3352acad964435935313`

```
$ docker run swarm create
Unable to find image 'swarm:latest' locally
latest: Pulling from library/swarm
ebe0176dcf9a: Pull complete 
19f771faa982: Pull complete 
902eeedf931a: Pull complete 
Digest: sha256:815fc8fd4617d866e1256999c2c0a55cc8f377f3dade26c3edde3f0543a70c04
Status: Downloaded newer image for swarm:latest
d9b20ca5682d3352acad964435935313
```

## Launch the Swarm manager


