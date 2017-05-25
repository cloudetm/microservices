# Use Docker Machine to simulate deployment with a VM

> Install Docker-Machine

https://docs.docker.com/machine/install-machine/

```
# curl -L https://github.com/docker/machine/releases/download/v0.10.0/docker-machine-`uname -s`-`uname -m` >/tmp/docker-machine &&
    chmod +x /tmp/docker-machine &&
    sudo cp /tmp/docker-machine /usr/local/bin/docker-machine
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   600    0   600    0     0   1476      0 --:--:-- --:--:-- --:--:--  1477
100 24.1M  100 24.1M    0     0  2871k      0  0:00:08  0:00:08 --:--:-- 4187k

# which docker-machine
/usr/local/bin/docker-machine

# docker-machine ls
NAME   ACTIVE   DRIVER   STATE   URL   SWARM   DOCKER   ERRORS
```

