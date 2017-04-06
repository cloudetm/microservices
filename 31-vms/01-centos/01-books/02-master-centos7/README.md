# Mastering CentOS 7 Linux Server

https://www.safaribooksonline.com/library/view/mastering-centos-7/9781785282393/

> run as root

```
[vagrant@localhost ~]$ su root
Password: vagrant
[root@localhost vagrant]#
```

> add user

```
[root@localhost vagrant]# useradd testuser
[root@localhost vagrant]# ls /home
testuser  vagrant
[root@localhost vagrant]# cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
...
testuser:x:1001:1001::/home/testuser:/bin/bash
[root@localhost vagrant]# passwd testuser # change password, unlock user
Changing password for user testuser.
New password: mastercentos
Retype new password: mastercentos
passwd: all authentication tokens updated successfully.
```

> add users to a group

```
[root@localhost vagrant]# groupadd testgroup
[root@localhost vagrant]# useradd testuser1 -G testgroup
[root@localhost vagrant]# passwd testuser1
Changing password for user testuser1.
New password: 
Retype new password: 
passwd: all authentication tokens updated successfully.
[root@localhost vagrant]# useradd testuser2 -g 1002
[root@localhost vagrant]# passwd testuser2
Changing password for user testuser2.
New password: 
Retype new password: 
passwd: all authentication tokens updated successfully.
[root@localhost vagrant]# cat /etc/group
root:x:0:
...
testuser:x:1001:
testgroup:x:1002:testuser1
testuser1:x:1003:
[root@localhost vagrant]# 
```

The difference between -g and -G is: 
with -G, we create the user with its default group and assign the user to the common testgroup as well, 
but with -g, we create the user as part of the testgroup only.
