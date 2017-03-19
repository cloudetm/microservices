# Containerizing Microservices with Docker

> Launch `Docker Terminal`

> List VirtualBox VMs

```
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER            ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v17.03.1-ce-rc1   
```

> Stop docker default

```
$ docker-machine stop default
Stopping "default"...
Machine "default" was stopped.
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL   SWARM   DOCKER    ERRORS
default   -        virtualbox   Stopped                 Unknown  
```

> Remove docker default

$ docker-machine rm default

> Create docker default

```
$ docker-machine create -d virtualbox --virtualbox-memory=4096 --virtualbox-cpu-count=4 default
Running pre-create checks...
Creating machine...
(default) Copying /Users/whan/.docker/machine/cache/boot2docker.iso to /Users/whan/.docker/machine/machines/default/boot2docker.iso...
(default) Creating VirtualBox VM...
(default) Creating SSH key...
(default) Starting the VM...
(default) Check network to re-create if needed...
(default) Waiting for an IP...
Waiting for machine to be running, this may take a few minutes...
Detecting operating system of created instance...
Waiting for SSH to be available...
Detecting the provisioner...
Provisioning with boot2docker...
Copying certs to the local machine directory...
Copying certs to the remote machine...
Setting Docker configuration on the remote daemon...
Checking connection to Docker...
Docker is up and running!
To see how to connect your Docker Client to the Docker Engine running on this virtual machine, run: docker-machine env default
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER            ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v17.03.1-ce-rc1   
```

> Start docker default if it is stopped

$ docker-machine start default

> Make shell ready to use the docker-machine instance

$ eval $(docker-machine env default)

> Open `VirtualBox` to see the Docker host is running

> Spin off the first Docker container

```
$ docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
78445dd45222: Pull complete 
Digest: sha256:c5515758d4c5e1e838e9cd307f6c6a0d620b5e07e6f927b07d05f6d12a1ac8d7
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://cloud.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/
```

> List docker images

$ docker images

> List running containers

```
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                      PORTS               NAMES
db29e8d1274f        hello-world         "/hello"            22 minutes ago      Exited (0) 22 minutes ago                       distracted_wilson
```

> Create Dockerfile

at /01-microservices-deployment-cookbook/01-building-microservices-with-java/01-server/Dockerfile

> `docker build -t packt/geolocation .`

`docker build` - build docker image from a Dockerfile

`-t packt/geolocation` - REPOSITORY name

`.` - it says that the Dockerfile is located in the current directory

```
/01-server/$ docker build -t packt/geolocation .
Sending build context to Docker daemon 15.13 MB
Step 1/5 : FROM openjdk:8
 ---> c59e84b8bbbf
Step 2/5 : RUN mkdir -p /opt/packt/geolocation
 ---> Using cache
 ---> 2020b36ce227
Step 3/5 : ADD target/app-1.0-SNAPSHOT.jar /opt/packt/geolocation/
 ---> Using cache
 ---> 4e0bcafd5d91
Step 4/5 : EXPOSE 8080
 ---> Using cache
 ---> bbe54b325f34
Step 5/5 : CMD java -jar /opt/packt/geolocation/app-1.0-SNAPSHOT.jar
 ---> Using cache
 ---> 9c1e33fdd8da
Successfully built 9c1e33fdd8da
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
packt/geolocation   latest              9c1e33fdd8da        7 minutes ago       656.3 MB
openjdk             8                   c59e84b8bbbf        2 weeks ago         641.5 MB
hello-world         latest              48b5124b2768        9 weeks ago         1.84 kB
```

> `$ docker run packt/geolocation` <-- NOT working

curl command won't work because the microservice is not running on your local computer;
It is running inside the container which is running inside your Docker host.
So we have to replace localhost in your CURL command with the IP of the container. 

> `$ docker run -p 8080:8080 packt/geolocation` - run container image

`-p` does the port mapping from Docker host to container.
Docker host port number: the left of the colon `8080:`
Container port number: the right of the colon `:8080`

```
$ docker run -p 8080:8080 packt/geolocation
INFO  [2017-03-18 05:55:15,913] org.eclipse.jetty.util.log: Logging initialized @975ms
INFO  [2017-03-18 05:55:15,970] io.dropwizard.server.DefaultServerFactory: Registering jersey handler with root path prefix: /
INFO  [2017-03-18 05:55:15,971] io.dropwizard.server.DefaultServerFactory: Registering admin handler with root path prefix: /
INFO  [2017-03-18 05:55:15,974] io.dropwizard.server.DefaultServerFactory: Registering jersey handler with root path prefix: /
INFO  [2017-03-18 05:55:15,974] io.dropwizard.server.DefaultServerFactory: Registering admin handler with root path prefix: /
INFO  [2017-03-18 05:55:15,975] io.dropwizard.server.ServerFactory: Starting App
INFO  [2017-03-18 05:55:16,047] org.eclipse.jetty.setuid.SetUIDListener: Opened application@6731787b{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
INFO  [2017-03-18 05:55:16,047] org.eclipse.jetty.setuid.SetUIDListener: Opened admin@16f7b4af{HTTP/1.1,[http/1.1]}{0.0.0.0:8081}
INFO  [2017-03-18 05:55:16,049] org.eclipse.jetty.server.Server: jetty-9.3.z-SNAPSHOT
INFO  [2017-03-18 05:55:16,454] io.dropwizard.jersey.DropwizardResourceConfig: The following paths were found for the configured resources:

    GET     /contacts (com.company.app.resource.ContactResource)
    POST    /contacts (com.company.app.resource.ContactResource)
    DELETE  /contacts/{id} (com.company.app.resource.ContactResource)
    GET     /contacts/{id} (com.company.app.resource.ContactResource)
    PUT     /contacts/{id} (com.company.app.resource.ContactResource)
    GET     /geolocation (com.company.app.resource.GeoLocationResource)
    POST    /geolocation (com.company.app.resource.GeoLocationResource)

INFO  [2017-03-18 05:55:16,456] org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@5c748168{/,null,AVAILABLE}
INFO  [2017-03-18 05:55:16,460] io.dropwizard.setup.AdminEnvironment: tasks = 

    POST    /tasks/log-level (io.dropwizard.servlets.tasks.LogConfigurationTask)
    POST    /tasks/gc (io.dropwizard.servlets.tasks.GarbageCollectionTask)

WARN  [2017-03-18 05:55:16,460] io.dropwizard.setup.AdminEnvironment: 
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!    THIS APPLICATION HAS NO HEALTHCHECKS. THIS MEANS YOU WILL NEVER KNOW      !
!     IF IT DIES IN PRODUCTION, WHICH MEANS YOU WILL NEVER KNOW IF YOU'RE      !
!    LETTING YOUR USERS DOWN. YOU SHOULD ADD A HEALTHCHECK FOR EACH OF YOUR    !
!         APPLICATION'S DEPENDENCIES WHICH FULLY (BUT LIGHTLY) TESTS IT.       !
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
INFO  [2017-03-18 05:55:16,463] org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@46678e49{/,null,AVAILABLE}
INFO  [2017-03-18 05:55:16,468] org.eclipse.jetty.server.AbstractConnector: Started application@6731787b{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
INFO  [2017-03-18 05:55:16,468] org.eclipse.jetty.server.AbstractConnector: Started admin@16f7b4af{HTTP/1.1,[http/1.1]}{0.0.0.0:8081}
INFO  [2017-03-18 05:55:16,468] org.eclipse.jetty.server.Server: Started @1531ms
```

> in-memory `jetty` servlet container instance is running on port 8080

INFO  [2017-03-18 05:55:16,468] org.eclipse.jetty.server.AbstractConnector: Started application@6731787b{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}

> Find the `IP` of the Docker Machine VM

```
$ docker-machine ls
NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER            ERRORS
default   *        virtualbox   Running   tcp://192.168.99.100:2376           v17.03.1-ce-rc1   
$ docker-machine ip default
192.168.99.100
$ docker ps -a
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS                           PORTS                    NAMES
8871127418b3        packt/geolocation   "java -jar /opt/packt"   About an hour ago   Up About an hour                 0.0.0.0:8080->8080/tcp   dreamy_wiles
```

> Http POST

$ curl -H "Content-Type: application/json" -X POST -d'{"timestamp": 1468203975, "userId": "f1196aac-470e-11e6-beb8-9e71128cae77", "latitude": 41.803488, "longitude":-88.144040}' http://192.168.99.100:8080/geolocation

> Http GET

```
$ curl http://192.168.99.100:8080/geolocation
[{"latitude":41.803488,"longitude":-88.14404,"userId":"f1196aac-470e-11e6-beb8-9e71128cae77","timestamp":1468203975}]
```
