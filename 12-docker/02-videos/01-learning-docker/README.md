# Learning Docker

https://www.safaribooksonline.com/library/view/learning-docker/9781491956885/


> `docker restart jolly_payne` - restart container image

## List all images and containers

```
# List all running containers
docker ps
# List all containers includes stopped ones
docker ps -a
# List all images
docker images
```

## Remove all images and containers

```
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)
```

