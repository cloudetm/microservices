# 2.2 Create a Docker container for each app

https://www.safaribooksonline.com/library/view/docker-orchestration-and/9780134398747/DOCK_02_02.html

## Dockerizing

- Dockerizing can be defined as modifying an app to run inside Docker containers

- Commonly includes also making a custom Docker image for the application

- Each microservice must be Dockerized

## Dockerizing Hurdles

- Configuration is typically done via environment variables. If your app is configured via files, some adjustments 
  might be required
  
- Properly capturing the logging of your application

- Authoring a robust Dockerfile can be tricky

## Avoid Reinventing the Wheel

- There are many high quality official Docker images on Docker Hub to start from

- A great way to start out to use one of these as a base in your FROM statement

- After specifying the base image, you can add your own customizations

- Be very careful with base images, and be sure to review the code for malicious content! (Anyone can push)

## Dockerizing a Basic Express Restful API

- This process applies to any back end web server

- The Express framework for Node.js is lightweight and easy to understand.

## Basic Express App

- Application code (server.js)

- Application dependencies (package.json)

- System dependencies (MongoDB)

## Official Node.js Docker Image

https://hub.docker.com/_/node/
