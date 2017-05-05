# Beginner's Guide

https://nginx.org/en/docs/beginners_guide.html

> start nginx in vagrant centos

```
$ vagrant up
$ vagrant ssh # user=vagrant password=vagrant

$ su root
Password: vagrant

yum install -y epel-release
yum install -y nginx

systemctl start nginx

http://localhost:8080/ - from Mac
```

## Start, Stop, Reload, Configuration

https://nginx.org/en/docs/beginners_guide.html

> graceful shutdown

```
nginx -s quit
```

> start nginx

```
service nginx start
```

> Configuration

```
# vi /etc/nginx/nginx.conf
```
