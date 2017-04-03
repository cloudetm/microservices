# 2.4. ELIMINATING METACONFLICTS: BUILDING A WEBSITE FARM

## Container ID

> Assign container ID to a shell variable

```
$ CID=$(docker create nginx:latest)
$ echo $CID
452e0968ceacf42d0cd40dd52aa9b12ad8c48fc61595fe6acfb93fddb63eca76
```

## 2.4.2 Container state and dependencies

> website provision script

```
$ MAILER_CID=$(docker run -d dockerinaction/ch2_mailer)
$ WEB_CID=$(docker create nginx)
$ AGENT_CID=$(docker create --link $WEB_CID:insideweb --link $MAILER_CID:insidemailer dockerinaction/ch2_agent)
```

> List running containers

$ docker ps

> List all containers

$ docker ps -a

