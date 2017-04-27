# Serving Flask uWSGI With Nginx - ubuntu

http://vladikk.com/2013/09/12/serving-flask-with-nginx-on-ubuntu/

## Nginx

> add Nginx repositories to apt-get sources

add-apt-repository ppa:nginx/stable

> update and upgrade packages

apt-get update && apt-get upgrade

> Install and start Nginx

```
apt-get install nginx
/etc/init.d/nginx start
```

> Test - default exposed port is 80

http://localhost/

## flask app

> create www flask app folder

mkdir /var/www/demoapp

> change ownership to logged in user william

chown -R william:william /var/www/demoapp/

> Install the virtualenv package

apt-get install python-virtualenv

> Create and activate a virtual environment, and install Flask

```
cd /var/www/demoapp
virtualenv venv
. venv/bin/activate
pip install flask
```

> Create hello.py

nano hello.py

```
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World!"

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080)
```

> Execute python hello.py

python hello.py

> Test

http://localhost:8080/

## uWSGI

> Install required compilers and tools

```
apt-get install build-essential python python-dev
pip install uwsgi
```

## Configuring Nginx

> Remove default site configuration

```
rm /etc/nginx/sites-enabled/default
```

> Add a new configuration file for our application

/var/www/demoapp/demoapp_nginx.conf

```
server {
    listen      80;
    server_name localhost;
    charset     utf-8;
    client_max_body_size 75M;

    location / { try_files $uri @yourapplication; }
    location @yourapplication {
        include uwsgi_params;
        uwsgi_pass unix:/var/www/demoapp/demoapp_uwsgi.sock;
    }
}
```

> symlink the new file to nginx's configuration files directory

ln -s /var/www/demoapp/demoapp_nginx.conf /etc/nginx/conf.d/

> Restart Nginx

/etc/init.d/nginx restart

> Test

```
http://localhost/
502 Bad Gateway
```

## Configuring uWSGI

> Add a new uWSGI configuration file

/var/www/demoapp/demoapp_uwsgi.ini

```
[uwsgi]
#application's base folder
base = /var/www/demoapp

#python module to import
app = hello
module = %(app)

home = %(base)/venv
pythonpath = %(base)

#socket file's location
socket = /var/www/demoapp/%n.sock

#permissions for the socket file
chmod-socket    = 666

#the variable that holds a flask application inside the module imported at line #6
callable = app

#location of log files
logto = /var/log/uwsgi/%n.log
```

> Create a new directory for uwsgi log files

mkdir -p /var/log/uwsgi

> Change its owner to the user

chown -R william:william /var/log/uwsgi

> Execute uWSGI

uwsgi --ini /var/www/demoapp/demoapp_uwsgi.ini

> Test

```
http://localhost/
Hello World!
```

## uWSGI Emperor - DO NOT WORK

> Create a new upstart configuration to execute emperor

/etc/init/uwsgi.conf

```
description "uWSGI"
start on runlevel [2345]
stop on runlevel [06]
respawn

env UWSGI=/var/www/demoapp/venv/bin/uwsgi
env LOGTO=/var/log/uwsgi/emperor.log

exec $UWSGI --master --emperor /etc/uwsgi/vassals --die-on-term --uid www-data --gid www-data --logto $LOGTO
```

> Create folder and symlink the configuration file

```
mkdir /etc/uwsgi && mkdir /etc/uwsgi/vassals
ln -s /var/www/demoapp/demoapp_uwsgi.ini /etc/uwsgi/vassals
```

> change ownership to logged in user william

```
chown -R www-data:www-data /var/www/demoapp/
chown -R www-data:www-data /var/log/uwsgi/
```

> Change uwsgi config chmod-socket from 666 to 644

/var/www/demoapp/demoapp_uwsgi.ini

```
...
#permissions for the socket file
chmod-socket    = 644
```

> Start the uWSGI job

start uwsgi
