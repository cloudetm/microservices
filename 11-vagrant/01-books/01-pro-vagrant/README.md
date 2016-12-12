# Pro Vagrant

https://www.safaribooksonline.com/library/view/pro-vagrant/9781484200735/

## Common commands

- `$ vagrant up` <- turn the environment on
- `$ vagrant halt` 
- `$ vagrant destroy` <- stop the application

## `$ vagrant up`

> Stage 1: downloading and install the box in the system - image dir: `cd ~/.vagrant.d/boxes`

`$ ls -l ~/.vagrant.d/boxes/` to verify

> Stage 2: importing the base box into the project such as VirtualBox - image dir: `cd ~/VirtualBox VMs/`

`$ ls -l ~/VirtualBox\ VMs/` to verify

> Stage 3: booting the system

**Files and directories relevant to the $ vagrant up command**

*Project's directory*

- Directory: `songs-app-sinatra` (vagrant project)
- Vagrantfile - file: it goes to VCS (version control)
- .vagrant/ - dir: it doesn't go to VCS

*VM Template directory*

- Directory: `~/.vagrant.d/boxes/http:-VAGRANTSLASH...box`

*VM instance directory*

- Directory: `~/VirtualBox VMs/songs-app-sinatra_XXX`

