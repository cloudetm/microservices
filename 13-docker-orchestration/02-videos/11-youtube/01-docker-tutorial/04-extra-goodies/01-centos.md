# youtube

https://www.youtube.com/watch?v=ZgjHM1XmAKI

> run as root

```
[vagrant@localhost ~]$ su root
Password: vagrant
```

```
[root@localhost vagrant]# docker search ubuntu
[root@localhost vagrant]# docker search -f=stars=3 ubuntu
NAME                       DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
ubuntu                     Ubuntu is a Debian-based Linux operating s...   5831      [OK]       
rastasheep/ubuntu-sshd     Dockerized SSH service, built on top of of...   78                   [OK]
ubuntu-upstart             Upstart is an event-based replacement for ...   71        [OK]       
consol/ubuntu-xfce-vnc     Ubuntu container with "headless" VNC sessi...   46                   [OK]

[root@localhost vagrant]# docker pull ubuntu

```
