# openstack centos nginx

> create centos

create a centos7 vm in openstack

ssh into the centos

> install nginx

https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-centos-7

```
sudo yum install -y epel-release
sudo yum install -y nginx
```

> start nginx

```
sudo systemctl start nginx
```

> Testing

- get the centos vm ip address `88.88.88.8`

- go to http://88.88.88.8/
 output: Welcome to nginx

