# installation

## install rabbitmq

```
~# apt-get install rabbitmq-server
~# rabbitmq-plugins enable rabbitmq_management
~# service rabbitmq-server restart
~# curl ifconfig.me <- get ip
Go to http://localhost:15672/ if ip does not work
Login - Username: guest  Password: guest
Click “Admin” tab
Click “Virtual Hosts” tag on the right side of page, expand “Add a new virtual host”, Name: “/sensu”, click “Add virtual host” button
Click “Users” tag on the right side, expand “Add a user”, Username: sensu  Password: secret, click “Add user” button
After page reload, click user “sensu”, Virtual Host: “/sensu”, click “Set permission” button
```

## install redis

```
~# apt-get install redis-server
~# redis-cli ping
PONG
```

## install sensu

https://sensuapp.org/docs/latest/install-repositories

```
~# wget -q http://repos.sensuapp.org/apt/pubkey.gpg
~# ls
pubkey.gpg
~# apt-key add pubkey.gpg
OK
~# echo "deb     http://repositories.sensuapp.org/apt sensu main" | sudo tee /etc/apt/sources.list.d/sensu.list
~# apt-get update
~# apt-get install sensu
~# vi /etc/sensu/config.json.example
~# vi /etc/sensu/config.json
{
  "rabbitmq": {
	"host": "localhost",
	"vhost": "/sensu",
	"user": "sensu",
	"password": "secret"
  },
  "redis": {
	"host": "localhost"
  },
  "api": {
	"port": 4567
  }
}
~# /etc/init.d/sensu-server start
 * Starting sensu-server                                             	[ OK ]
~# /etc/init.d/sensu-api start
 * Starting sensu-api                                                	[ OK ]
~# /etc/init.d/sensu-server status
 * sensu-server (pid 8126) is running
root@pwillhan-VirtualBox:~# /etc/init.d/sensu-api status
 * sensu-api (pid 8159) is running
root@pwillhan-VirtualBox:~# tail /var/log/sensu/sensu-server.log
```