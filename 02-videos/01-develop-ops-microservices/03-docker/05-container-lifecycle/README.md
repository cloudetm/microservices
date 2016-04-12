# Container Lifecycle

1, create a docker file
```
FROM ubuntu
MAINTAINER pwillhan
RUN apt-get install -y software-properties-common
RUN add-apt-repository ppa:openjdk-r/ppa
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk
```

2, $ docker build .

Successfully built 2f4c2799b0c0

3, $ docker run 2f4c2799b0c0 java -version

4, $ docker tag 2f4c2799b0c0 pwillhan/microservice-java-base

5, $ docker login

6, $ docker push pwillhan/microservice-java-base

7, go to https://hub.docker.com/r/pwillhan/microservice-java-base/

