# Chapter 1: Getting Started with Vagrant

Vagrant simplifies the workflow and reduces the workload necessary to run and operate virtual machines (VMs).

- Offers a very simple command-line interface to manage VMs
- Supports all major virtual solutions: VirtualBox, VMWare, and Hyper-V
- Supports most popular software configuration tools, including Ansible, Chef, Puppet, and Salt
- Facilitates procedures to distribute and share virtual environments

> Without Vagrant

- Download the VM image
- Start the VM
- Configure the VM's shared directories and network interfaces
- Maybe install some software within the VM

> With Vagrant

- Automatically download and install a VM, if necessary
- Boot the VM
- Configure various resources: RAM, CPUs, network connections, and shared folders
- Install additional software within the VM by using tools such as Puppet, Chef, Ansible, and Salt

## Installing the Software

> Git

> VirtualBox

> Vagrant

https://www.vagrantup.com/docs/installation/

## Common commands

- `$ vagrant up` <- turn the environment on
- `$ vagrant halt` 
- `$ vagrant destroy` <- stop the application
