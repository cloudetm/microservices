# Configure Docker image to be pushed to Docker Hub

https://hub.docker.com

> Build Docker Image

```
# docker build -t pwillhan/myservice:latest .
# docker images
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
pwillhan/myservice   latest              28d78733908d        22 minutes ago      669MB
```

> Login to Docker Hub

```
# docker login
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: pwillhan
Password: 
Login Succeeded
```

> Push image to Dock Hub

```
[root@localhost vagrant]# docker push pwillhan/myservice

https://hub.docker.com/r/pwillhan/myservice/
```
