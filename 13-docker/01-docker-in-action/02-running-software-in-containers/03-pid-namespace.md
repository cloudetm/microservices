# 2.3 Solved problems and the PID namespace

> Start two containers

```
$ docker run -d --name namespaceA busybox:latest /bin/sh -c "sleep 30000"
$ docker run -d --name namespaceB busybox:latest /bin/sh -c "nc -l -p 0.0.0.0:80"
$ docker ps
CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS              PORTS               NAMES
ae87808300f4        busybox:latest              "/bin/sh -c 'nc -l -p"   4 hours ago         Up 4 hours                              namespaceB
361556aa554b        busybox:latest              "/bin/sh -c 'sleep 30"   4 hours ago         Up 4 hours                              namespaceA
```

> Exec commands in running containers

```
$ docker exec namespaceA ps
PID   USER     TIME   COMMAND
    1 root       0:00 /bin/sh -c sleep 30000
    7 root       0:00 sleep 30000
    8 root       0:00 ps
$ docker exec namespaceB ps
PID   USER     TIME   COMMAND
    1 root       0:00 /bin/sh -c nc -l -p 0.0.0.0:80
    7 root       0:00 nc -l -p 0.0.0.0:80
    8 root       0:00 ps    
```

> Software conflict - port conflict example
    
```
$ docker run -d --name webConflict nginx:latest
$ docker exec webConflict nginx -g 'daemon off;'
2017/04/03 00:02:06 [emerg] 8#8: bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
2017/04/03 00:02:06 [emerg] 8#8: bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
2017/04/03 00:02:06 [emerg] 8#8: bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
2017/04/03 00:02:06 [emerg] 8#8: bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
2017/04/03 00:02:06 [emerg] 8#8: bind() to 0.0.0.0:80 failed (98: Address already in use)
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
2017/04/03 00:02:06 [emerg] 8#8: still could not bind()
nginx: [emerg] still could not bind()
```

> Solve conflict problem - process isolation: run each program in a different container

```
$ docker run -d --name webA nginx:latest
d9f48e9b6b3f0b5962b3b4bbc90fd70d067763fe3c22d0035870d53c4e9f1e09
$ docker run -d --name webB nginx:latest
ce3ebefd74cefa422363cc6fd85d73657751e89feee74e7a3a41a1def0adb022
$ docker logs webA
$ docker logs webB
```

> Assign CID to a shell variable

```
$ CID=$(docker create nginx:latest)
$ echo $CID
452e0968ceacf42d0cd40dd52aa9b12ad8c48fc61595fe6acfb93fddb63eca76
```

> Container ID (CID) file

```
$ docker create --cidfile /tmp/web.cid nginx
c7c6cd9fee283a39fdc180ea6a5f157d5599cc85406d510aead3f8a3082c850d
$ cat /tmp/web.cid 
c7c6cd9fee283a39fdc180ea6a5f157d5599cc85406d510aead3f8a3082c850d
$ docker create --cidfile /tmp/web.cid nginx
Container ID file found, make sure the other container isn't running or delete /tmp/web.cid
```

> Get the truncated ID of the last created container

```
$ CID=$(docker ps --latest --quiet)
$ echo $CID
c7c6cd9fee28
$ CID=$(docker ps -l -q)
$ echo $CID
c7c6cd9fee28
```

