# The States of VM

> `$ vagrant global-status --proune`

`$ vagrant global-status` command caches the data about VMs and it could print outdated results.
The problem can be resolved with the --prune option which clears the cache.

## Vagrantfile

- `Vagrant.configure(2)` - 2 means version 2

```
Vagrant.configure(2) do |config|
  config.vm.box = "http://boxes.gajdaw.pl/sinatra/sinatra-v1.0.0.box"
  config.vm.network :forwarded_port, guest: 4567, host: 45670, host_ip: "127.0.0.1"
end
```

## Start/Stop VM

> `$ vagrant halt` - Power Off

> `$ vagrant up` - Start VM (Running)

## Start Application

```
$ vagrant ssh
$ ruby app.rb -o 0.0.0.0 &
open browser and go to http://127.0.0.1:45670/
ctrl+c
$ logout
```

## Saved VM

> `$ vagrant suspend` - Stop VM (Saved), unable to access http://127.0.0.1:45670/

> `$ vagrant up` - Restore VM, able to access http://127.0.0.1:45670/

## Summary

- `$ vagrant suspend` followed by `$ vagrant up`: The HTTP server started by hand is preserved

- `$ vagrant halt` followed by `$ vagrant up`: The HTTP server started by hand is killed and has to be started again manually

- `$ vagrant destroy` followed by `$ vagrant up`: The HTTP server started by hand is killed and has to be stared again manually

- `$ vagrant reload` reboot the VM

## Remove the Box

```
$ vagrant box list
$ vagrant box remove Name
```
