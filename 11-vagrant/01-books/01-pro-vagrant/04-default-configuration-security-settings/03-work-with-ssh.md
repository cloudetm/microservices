# Working with SSH

Secure Shell (SSH) is a mechanism that opens a remote terminal session to a computer running a u*it-like system.
 
> `$ ssh tom@example.net` - `tom` is user on `example.net` server

> `$ vagrant ssh` - it is the simplest way

> SSH configuration

```
$ vagrant ssh-config
Host default
  HostName 127.0.0.1
  User vagrant
  Port 2201
  UserKnownHostsFile /dev/null
  StrictHostKeyChecking no
  PasswordAuthentication no
  IdentityFile /Users/whan/vagrant-pro/chapter04/.vagrant/machines/default/virtualbox/private_key
  IdentitiesOnly yes
  LogLevel FATAL
```

> traditional ssh approach
 
```
$ ssh -p2201 vagrant@127.0.0.1
vagrant@127.0.0.1's password: 
vagrant@vagrant-ubuntu-trusty-32:~$ logout
```

```
username: vagrant
password: vagrant
```

## Allow everyone to access the guest OS

```
config.vm.network :forwarded_port, guest: 22, host: 9999
```

## Use authorized_keys file for SSH authorization

SSH protocol allows two different authorization methods

- username/password

```
$ ssh –p222 vagrant@127.0.0.1
```

- public/private cryptographic keys (two RSA keys)

**Host OS**

```
~/.ssh/id_rsa     - private key
~/.ssh/id_rsa.pub - public key
```

**Guest OS (VM)**

```
~/.ssh/authorized_keys
```

The private key is stored in the `host OS` in ~/.vagrant.d/insecure_private_key file

The public key is stored in the `guest OS inside /home/vagrant/.ssh/authorized_keys file
