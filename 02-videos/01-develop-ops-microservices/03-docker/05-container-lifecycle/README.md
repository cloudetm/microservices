# Container Lifecycle

```
$ docker ps -a <- show all containers
$ docker rm cass <- remove container by name
```

Left console
```
1, $ docker run -d --name cass spotify/cassandra
3, $ docker stop cass  <- graceful shutdown
4, $ docker restart cass
6, $ docker kill cass <- non-graceful shutdown
7, create a killme.rb "vi killme.rb"
at_exit do
  puts "shutting down!"
end
while true do
  puts "hi"
end
8, create a docker file "vi Dockerfile"
FROM alpine
RUN apk update
RUN apk add ruby
ADD killme.rb /
ENTRYPOINT ruby killme.rb
9, $ docker build .
Successfully built a32901d45aee
11, $ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS               NAMES
fff6a97f0ad4        a32901d45aee        "/bin/sh -c 'ruby kil"   15 seconds ago      Up 14 seconds                           jovial_cray
12, $ docker stop fff6a97f0ad4 <- the right console stop without the app "shutting down!"
13, modify the docker file "vi Dockerfile" <- use exact form, represent ENTRYPOINT as json like - the app will run inside container
FROM alpine
RUN apk update
RUN apk add ruby
ADD killme.rb /
ENTRYPOINT ["ruby", "killme.rb"]
14, docker build .
Successfully built b9119b3b908d
16, $ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
1ec6735b1ddd        b9119b3b908d        "ruby killme.rb"    13 seconds ago      Up 12 seconds                           jovial_stonebraker
17, $ docker stop 1ec6735b1ddd <- the right console stop with the app proper "shutting down!"
```

Right console
```
2, $ docker logs -f cass
5, $ docker logs -f cass
10, $ docker run a32901d45aee <- keep outputing "hi"
15, $ docker run b9119b3b908d
```
