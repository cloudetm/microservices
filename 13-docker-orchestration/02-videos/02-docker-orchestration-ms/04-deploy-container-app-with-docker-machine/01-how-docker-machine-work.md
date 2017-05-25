# Understand how Docker Machine works

> What Docker Machine is

- A way to remotely create, deploy, and manage Docker containers

- Has drivers to interface with various cloud providers (AWS, DigitalOcean, Azure)

- Also has drivers for local virtual machines

- Interfaces with Docker Compose and Docker Swarm

> How Docker Machine Works

- Create the underlying host, be it an AWS instance, a VirtualBox VM, or whichever driver you specify

- Installs a Docker Daemon on the host, that you can interact with remotely, via the Docker CLI on your
  host, as if it was your local Docker Daemon
  
- By default, this remote operations will be done over a secure TLS connection

- A ~/.docker/machine will also be created on your local machine

- This directory contains your cert files

- This directory also maintains a list of your Docker machines, the necessary information to find them,
  and various meta-data about your Docker Machines
  