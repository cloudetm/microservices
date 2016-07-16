# puppet

## installation
https://i.ytimg.com/vi/j4331uoHIR8/maxresdefault.jpg

## learning puppet manifests
https://www.youtube.com/watch?v=76qeLNMHgF4

### ubuntu

```
$ vagrant destroy
$ vagrant up
$ vagrant ssh
~$ sudo apt-get update
~$ sudo apt-get install vim
```

```
~$ uname -a <- display system information
Linux precise32 3.2.0-23-generic-pae #36-Ubuntu SMP Tue Apr 10 22:19:09 UTC 2012 i686 i686 i386 GNU/Linux
~$ sudo service ssh status
ssh start/running, process 4308
~$ sudo service ssh stop
ssh stop/waiting
~$ sudo service ssh start
ssh start/running, process 4459
```

```
~$ mkdir target
~$ mkdir source
~$ cd target
~/target$ vim foo.txt - hello, world!
~/target$ cd ~/source/
~/source$ vim foo.txt - hi, everyone!
~$ vim nodes.pp
package {'openssl':
        ensure => present,
        before => File['/home/vagrant/target/foo.txt'],
}
file {'ssh-config':
        ensure => file,
        path => '/home/vagrant/target/foo.txt',
        mode => 600,
        source => '/home/vagrant/source/foo.txt',
}
service {'ssh':
        ensure => running, #same as service ssh start
        enable => true, #same as chkconfig sshd on
        subscribe => File['/home/vagrant/target/foo.txt'],
}
~$ puppet apply nodes.pp
notice: /Stage[main]//File[ssh-config]/content: content changed '{md5}31efb10519b6858e07c7dc06c8bfabc1' to '{md5}195e764c37a2ec81c845260d73fad3c5'
err: /Stage[main]//Service[ssh]/ensure: change from stopped to running failed: Could not start Service[ssh]: Execution of '/sbin/start ssh' returned 1:  at /home/vagrant/nodes.pp:15
notice: /Stage[main]//Service[ssh]: Triggered 'refresh' from 1 events
notice: Finished catalog run in 0.08 seconds
```
