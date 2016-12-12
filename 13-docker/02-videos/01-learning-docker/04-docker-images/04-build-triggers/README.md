# Build Triggers

> `docker build -t shell .`

> `docker run shell`

```
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
```

> `docker build -t rickfast/rack .` - build docker image under `rack` folder

> `docker run -d -p 4567:4567 rickfast/app` - build docker image under `app` folder

> `curl 192.168.99.100:4567`

output:

```
Hello O'Reilly!
```
