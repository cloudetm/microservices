# Running Software in Containers

> `--detach`

`--detach` option and started the program in the background. 
This means that the program started but isn’t attached to your terminal.
A daemon generally interacts with other programs or humans over a network or some other communication tool.

> Start a container running nginx

$ docker run --detach --name web nginx:latest

> Start a container running mailer

$ docker run -d --name mailer dockerinaction/ch2_mailer

> Start a running interactive containers

```
$ docker run -it busybox
/ # ls
bin   dev   etc   home  proc  root  sys   tmp   usr   var
```

$ docker run --interactive --tty --link web:web --name web_test busybox:latest /bin/sh

*in container virtual terminal*

```
# wget -O - http://web:80/
# exit
```

> Start a container running the monitoring agent

$ docker run -it --name agent --link web:insideweb --link mailer:insidemailer dockerinaction/ch2_agent

`ctrl+p+q` to return to the shell of host computer

> List currently running containers

$ docker ps

> Restart container

`restart` is for background containers

`start -i` is for interactive container 

```
$ docker restart web
$ docker restart mailer
$ docker start -i agent
```

> Check system is operating correctly

```
$ docker logs web
$ docker logs mailer
$ docker logs agent
```

> Test agent triggers a call to the mailer when web server is down

```
$ docker stop web
web
$ docker logs mailer
CH2 Example Mailer has started.
Sending email: To: admin@work  Message: The service is down!
```
