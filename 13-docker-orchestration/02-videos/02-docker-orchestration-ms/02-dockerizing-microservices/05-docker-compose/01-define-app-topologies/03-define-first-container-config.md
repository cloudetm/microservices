# Defining our First Container's Config

- Instead of building/running our image directly via docker run, we can declare if the image should be built or pulled 
  in docker-compose.yml, and run it that way
- Another benefit of Docker Compose is it allows us to save our service development specific configs such as volumes and
  ports. This is a huge win, as Dockerfiles do not allow you to specify a container to host port mapping. As Docker 
  Compose files are meant to be more specific and less general purpose, they give us a greater degree of control
