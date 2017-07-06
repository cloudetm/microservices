# docker

> docker quickstart terminal

https://docs.docker.com/docker-for-mac/

https://docs.docker.com/engine/reference/run/

https://docs.docker.com/engine/reference/builder/

https://docs.docker.com/engine/reference/commandline/cli/

> commands

```
# docker -v

# List all running containers
docker ps
# List all containers includes stopped ones
docker ps -a
# List all images
docker images

# Delete all containers
docker rm $(docker ps -a -q)
# Force delete all containers
docker rm -f $(docker ps -a -q)

# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)
# Delete an image
docker rmi myservice
```

## note

> forever to starting

https://forums.docker.com/t/docker-is-starting-forever-solved-by-reinstall/13445

`removing ~/Library/Containers/com.docker.*`
