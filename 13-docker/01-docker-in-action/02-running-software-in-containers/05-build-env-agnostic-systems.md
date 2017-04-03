# 2.5. BUILDING ENVIRONMENT-AGNOSTIC SYSTEMS

## Read-only file systems

```
$ docker run -d --name wp --read-only wordpress:4
$ docker inspect --format "{{.State.Running}}" wp
false
$ docker run -d --name wpdb -e MYSQL_ROOT_PASSWORD=ch2demo mysql:5
$ docker run -d --name wp2 --link wpdb:mysql -p 80 --read-only wordpress:4
$ docker inspect --format "{{.State.Running}}" wp2
$ docker logs wp2
WordPress not found in /var/www/html - copying now...
Complete! WordPress has been successfully copied to /var/www/html
Mon Apr  3 16:48:36 2017 (22): Fatal Error Unable to create lock file: Bad file descriptor (9)
Mon Apr  3 16:48:36 2017 (31): Fatal Error Unable to create lock file: Bad file descriptor (9)
Mon Apr  3 16:48:36 2017 (40): Fatal Error Unable to create lock file: Bad file descriptor (9)
Mon Apr  3 16:48:36 2017 (49): Fatal Error Unable to create lock file: Bad file descriptor (9)
Mon Apr  3 16:48:37 2017 (63): Fatal Error Unable to create lock file: Bad file descriptor (9)
```

