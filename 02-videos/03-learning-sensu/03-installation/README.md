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

