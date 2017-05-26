# centos

> Start Docker

```
$ cd vagrant/centos/
$ vagrant up
$ vagrant ssh
$ su root
Password: vagrant
# systemctl start docker
```

> nano - installation

```
# yum install nano -y
```

> Install Wget

```
[root@localhost ~]# yum -y update
[root@localhost ~]# yum install wget -y
```

## Install VirtualBox

https://tecadmin.net/install-oracle-virtualbox-on-centos-redhat-and-fedora/

> Install VirtualBox

```
# cd /etc/yum.repos.d/
# wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo
# rpm -Uvh http://epel.mirror.net.in/epel/7/x86_64/e/epel-release-7-9.noarch.rpm
# yum install gcc make patch  dkms qt libgomp -y
# yum install kernel-headers kernel-devel fontforge binutils glibc-headers glibc-devel -y
# export KERN_DIR=/usr/src/kernels/2.6.32-504.3.3.el6.x86_64
# yum install VirtualBox-5.1 -y

# vboxmanage --version
```

> Rebuild kernel modules

https://github.com/cloudfoundry/bosh-lite/issues/363

```
# yum -y install kernel-headers kernel-devel
reboot
# /sbin/rcvboxdrv setup
# VBoxManage --version
```

