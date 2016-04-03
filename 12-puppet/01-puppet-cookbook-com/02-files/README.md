# Files

```
$ vagrant destroy
$ vagrant up
$ vagrant ssh
~$ sudo apt-get update
~$ sudo apt-get install vim
~$ uname -a <- display system information
Linux precise32 3.2.0-23-generic-pae #36-Ubuntu SMP Tue Apr 10 22:19:09 UTC 2012 i686 i686 i386 GNU/Linux
```

```
~$ puppet apply foo.pp
```

## create a directory
http://www.puppetcookbook.com/posts/creating-a-directory.html

```
~$ vim createDir.pp
# create a directory
file{'/etc/site-conf':
  ensure => 'directory',
}
~$ ls /etc/site-conf <- list directory
ls: cannot access /etc/site-conf: No such file or directory
~$ sudo puppet apply createDir.pp
notice: /Stage[main]//File[/etc/site-conf]/ensure: created
notice: Finished catalog run in 0.03 seconds
~$ ls /etc/site-conf
~$ sudo rmdir /etc/site-conf <- delete directory
```
