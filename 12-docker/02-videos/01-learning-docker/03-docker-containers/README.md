# Docker Containers

## Getting Started With Containers

> run `eval $(docker-machine env default)` when you have following error 

```
docker: Cannot connect to the Docker daemon. Is the docker daemon running on this host?.
See 'docker run --help'.
```

> `docker run ubuntu pwd` - run an ubuntu container

> `docker run -i -t ubuntu /bin/bash` - run container in interactive mode

```
root@501c9dcdb4d2:/# hostname
501c9dcdb4d2
root@501c9dcdb4d2:/# ls
bin  boot  dev  etc  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
root@501c9dcdb4d2:/# 
root@501c9dcdb4d2:/# ps -A
  PID TTY          TIME CMD
    1 ?        00:00:00 bash
   16 ?        00:00:00 ps
root@501c9dcdb4d2:/# exit
exit   
```

> https://hub.docker.com/

## Running Containers

> `docker run ubuntu` - run ubuntu short live container

> `docker run ubuntu ls`

> `docker run rickfast/hello-oreilly-http` - run long live container foreground; `ctrl+c` to stop it.

> `run -d rickfast/hello-oreilly-http` run long live container detached (background) 

```
9d8b785d4dbf9a51a3405d246ec6c6482bf395d31b2556695715c0fb4b7cac0b
```

> `docker ps` - list all running containers

```
CONTAINER ID        IMAGE                         COMMAND             CREATED             STATUS              PORTS                    NAMES
9d8b785d4dbf        rickfast/hello-oreilly-http   "ruby hello.rb"     6 minutes ago       Up 6 minutes        4567/tcp                 distracted_kare
```

> `docker stop 9d8b` - stop a container

> `docker ps` - list all running containers

```
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

> `docker ps -a` - list all stopped containers

> `docker run -d --name hello_container1 rickfast/hello-oreilly-http` - create a new container named `hello_container1`

```
fa470b983ebbbe525c633244c3dda71482a1fd2cbd4dea02884e03c21ebbf12d
```

> `docker ps` - list all running containers

```
CONTAINER ID        IMAGE                         COMMAND             CREATED              STATUS              PORTS               NAMES
fa470b983ebb        rickfast/hello-oreilly-http   "ruby hello.rb"     About a minute ago   Up About a minute   4567/tcp            hello_container1
```

> `docker restart hello_container1` - restart a container by using name

> `docker rm hello_container1` - remove a container named `hello_container1`

> `docker rm $(docker ps -a -q)` - remove all containers including stopped ones

## Running Containerized Web Applications

> `docker run -d -P --name redis redis` - launch redis container image

> `docker ps`

```
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                     NAMES
3b046907867b        redis               "/entrypoint.sh redis"   28 seconds ago      Up 27 seconds       0.0.0.0:32768->6379/tcp   redis
```

> `docker run --link redis -i -t ubuntu /bin/bash` - link ubuntu container to redis container

```
root@f81526da1e33:/# env
REDIS_ENV_REDIS_DOWNLOAD_URL=http://download.redis.io/releases/redis-3.0.7.tar.gz
REDIS_PORT_6379_TCP_PROTO=tcp  <-- HERE: REDIS_PORT_6379_TCP_PROTO
HOSTNAME=f81526da1e33
REDIS_ENV_REDIS_DOWNLOAD_SHA1=e56b4b7e033ae8dbf311f9191cf6fdf3ae974d1c
TERM=xterm
REDIS_NAME=/silly_panini/redis
REDIS_PORT_6379_TCP_ADDR=172.17.0.2
LS_COLORS=rs=0:di=01;34:ln=01;36:mh=00:pi=40;33:so=01;35:do=01;35:bd=40;33;01:cd=40;33;01:or=40;31;01:su=37;41:sg=30;43:ca=30;41:tw=30;42:ow=34;42:st=37;44:ex=01;32:*.tar=01;31:*.tgz=01;31:*.arj=01;31:*.taz=01;31:*.lzh=01;31:*.lzma=01;31:*.tlz=01;31:*.txz=01;31:*.zip=01;31:*.z=01;31:*.Z=01;31:*.dz=01;31:*.gz=01;31:*.lz=01;31:*.xz=01;31:*.bz2=01;31:*.bz=01;31:*.tbz=01;31:*.tbz2=01;31:*.tz=01;31:*.deb=01;31:*.rpm=01;31:*.jar=01;31:*.war=01;31:*.ear=01;31:*.sar=01;31:*.rar=01;31:*.ace=01;31:*.zoo=01;31:*.cpio=01;31:*.7z=01;31:*.rz=01;31:*.jpg=01;35:*.jpeg=01;35:*.gif=01;35:*.bmp=01;35:*.pbm=01;35:*.pgm=01;35:*.ppm=01;35:*.tga=01;35:*.xbm=01;35:*.xpm=01;35:*.tif=01;35:*.tiff=01;35:*.png=01;35:*.svg=01;35:*.svgz=01;35:*.mng=01;35:*.pcx=01;35:*.mov=01;35:*.mpg=01;35:*.mpeg=01;35:*.m2v=01;35:*.mkv=01;35:*.webm=01;35:*.ogm=01;35:*.mp4=01;35:*.m4v=01;35:*.mp4v=01;35:*.vob=01;35:*.qt=01;35:*.nuv=01;35:*.wmv=01;35:*.asf=01;35:*.rm=01;35:*.rmvb=01;35:*.flc=01;35:*.avi=01;35:*.fli=01;35:*.flv=01;35:*.gl=01;35:*.dl=01;35:*.xcf=01;35:*.xwd=01;35:*.yuv=01;35:*.cgm=01;35:*.emf=01;35:*.axv=01;35:*.anx=01;35:*.ogv=01;35:*.ogx=01;35:*.aac=00;36:*.au=00;36:*.flac=00;36:*.mid=00;36:*.midi=00;36:*.mka=00;36:*.mp3=00;36:*.mpc=00;36:*.ogg=00;36:*.ra=00;36:*.wav=00;36:*.axa=00;36:*.oga=00;36:*.spx=00;36:*.xspf=00;36:
REDIS_PORT_6379_TCP_PORT=6379
REDIS_ENV_GOSU_VERSION=1.7
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
PWD=/
REDIS_PORT_6379_TCP=tcp://172.17.0.2:6379
SHLVL=1
HOME=/root
REDIS_PORT=tcp://172.17.0.2:6379
REDIS_ENV_REDIS_VERSION=3.0.7
LESSOPEN=| /usr/bin/lesspipe %s
LESSCLOSE=/usr/bin/lesspipe %s %s
_=/usr/bin/env
root@f81526da1e33:/# exit
exit
```

> `run -d --link redis --name web rickfast/oreilly-simple-web-app` - launch web app container and link to redis container

http://192.168.99.100:4567/ does not work because container port is not mapped to docker host

> `docker kill web`

> `docker rm web`

> `docker run -p 4567:4567 --link redis --name web rickfast/oreilly-simple-web-app` - map container port 4567 to docker host 4567

> go to http://192.168.99.100:4567/

```
Dockerized Web Application!

The data in the Redis database is
```

> http://192.168.99.100:4567/load/hello - load data "hello" to redis

> refresh http://192.168.99.100:4567/ - verify data is loaded

```
Dockerized Web Application!

The data in the Redis database is hello
```

> `docker rm web`

> `docker run -P --link redis --name web -d rickfast/oreilly-simple-web-app` - "-P" bind to random available port

```
070e0e6b0c778a3bc17555ae66b3ae4a235de1169881513b84c46ce800f7d810
```

> `docker ps -l` - list last run container only to find out the port

```
CONTAINER ID        IMAGE                             COMMAND             CREATED              STATUS              PORTS                     NAMES
070e0e6b0c77        rickfast/oreilly-simple-web-app   "ruby app.rb"       About a minute ago   Up About a minute   0.0.0.0:32769->4567/tcp   web
```

> `docker port web` - list named container port

```
4567/tcp -> 0.0.0.0:32769
```

## Configuring Containerized Applications

> `docker run -e "HELLO=OREILLY" ubuntu /bin/bash -c export` - inject environment variable to container by using "-e"

```
declare -x HELLO="OREILLY"
declare -x HOME="/root"
declare -x HOSTNAME="fbdd213d1439"
declare -x OLDPWD
declare -x PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
declare -x PWD="/"
declare -x SHLVL="1"
```

> `docker stop redis`

> `rm redis`

> `docker run -d -P --name redis redis` 

```
24eaa8bcc66aa74af5a8a6189215197aa10d0836eaf77b3fec0d42eb51683a73
```

> `docker inspect redis` - get IPAddress of the container

```
"IPAddress": "172.17.0.2",
```

> `docker run -d -e "REDIS_PORT_6379_TCP_ADDR=172.17.0.2" --name web -p 4567:4567 rickfast/oreilly-simple-web-app` - inject env variable IPAddress to web app container

```
d726ca1b4ece894877ed45bf021c493ef04a33593b6adf43ddafbd86ce29a51f
```

> http://192.168.99.100:4567/load/DATA - load data into redis

> http://192.168.99.100:4567/ - verify data is loaded

```
Dockerized Web Application!

The data in the Redis database is DATA
```

## Container Lifecycle

> `docker run --name hello rickfast/hello-oreilly` - launch short live container

```
Hello O'Reilly!
```

> `docker ps -a` - STATUS = Exited

```
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS                     PORTS               NAMES
c60959620c83        rickfast/hello-oreilly   "echo 'Hello O'Reilly"   3 seconds ago       Exited (0) 3 seconds ago                       hello
```

> `docker restart hello` - create a identical one, and see a container name

```
hello
```

> `docker run rickfast/oreilly-shut-me-down` and then ctrl+c - graceful shutdown

> `docker run --name shutmedown -d rickfast/oreilly-shut-me-down` - run the shutmedown container in the background

```
89caf0b21c134803e226cf9fd8295e522f88db8e62be267944077f762a16ae25
```

> in another terminal `docker logs -f shutmedown` - watch the shutmedown container

> in the first terminal `docker stop --time 10 shutmedown` - graceful shutdown by waiting for 10 seconds

> in the other terminal, you will see following output

```
graceful shutdown complete
```

> in the first terminal `docker stop --time 1 shutmedown` - no graceful shutdown by waiting for just 1 second

> in the other terminal, no graceful shutdown output

> `docker kill shutmedown` - kill a container flat out

## Restart Policy

> `docker run --name timebomb -d -p 4567:4567 rickfast/oreilly-time-bomb` - run a container

```
42b5de88149246534c57350f4c86704c072bc9428f73133bf40ec611926f722e
```

> in another terminal `docker logs -f timebomb` - watch the timebomb container

> in the first terminal `curl 192.168.99.100:4567` - it will cause the `timebomb` container to shutdown

> `docker run -d -p 4567:4567 --name timebomb --restart unless-stopped rickfast/oreilly-time-bomb` - restart unless stop

```
e7067f6275b2d24b3c8f1310653a2004808d58ac64143422fc5fcdf6883ba371
```

> `curl 192.168.99.100:4567`

> `docker ps`

```
CONTAINER ID        IMAGE                        COMMAND             CREATED             STATUS              PORTS                    NAMES
e7067f6275b2        rickfast/oreilly-time-bomb   "ruby app.rb"       7 seconds ago       Up 1 seconds        0.0.0.0:4567->4567/tcp   timebomb
```

> `docker kill timebomb` - need to kill the container to stop

> `docker rm timebomb` - remove the container

## Debugging Containers

> `docker ps` - list all running containers

> `docker ps -a` - list all containers includes stopped containers

> `docker ps -l` list the last run container

> `docker run -d -p 6379:6379 --name redis redis` - run redis container

```
a345f0b79a2ffa2cf6343caa6ae73f23c4e27f2f395fd978803bb4abb2522d68
```

> `docker logs redis` - see the current logging output

> `docker logs -f redis` - follow the log until ctrl+c

> `docker inspect redis` - get container local IPAddress

> `docker inspect --format='{{.NetworkSettings.IPAddress}}' redis`

```
172.17.0.2
```

> `docker run -i -t ubuntu /bin/bash` - get into ubuntu container interactive shell

```
root@3c34409e7099:/# ls
bin  boot  dev  etc  home  lib  lib64  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
root@3c34409e7099:/# exit
exit
```

> `docker exec -i -t redis /bin/bash` - `exec` runs a secondary interactive shell inside a running container

```
root@a345f0b79a2f:/data# ps -A  <- see all running processes
  PID TTY          TIME CMD
    1 ?        00:00:25 redis-server
   14 ?        00:00:00 bash
   19 ?        00:00:00 ps
root@a345f0b79a2f:/data# exit
exit
```
