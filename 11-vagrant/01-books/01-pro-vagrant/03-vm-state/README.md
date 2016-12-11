# The States of VM

> $ vagrant global-status --proune

`$ vagrant global-status` command caches the data about VMs and it could print outdated results.
The problem can be resolved with the --prune option which clears the cache.

## Vagrantfile

- `Vagrant.configure(2)` - 2 is version 2

```
Vagrant.configure(2) do |config|
  config.vm.box = "http://boxes.gajdaw.pl/sinatra/sinatra-v1.0.0.box"
  config.vm.network :forwarded_port, guest: 4567, host: 45670, host_ip: "127.0.0.1"
end
```
