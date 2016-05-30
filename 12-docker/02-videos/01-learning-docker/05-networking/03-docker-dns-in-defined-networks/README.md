# Docker DNS In User Defined Networks

> `docker network create --driver bridge dns-test` - define a new bridge network `dns-test`

```
5ba18807737b92586fe947e4c86785f7571b8e1fe9247315e00e9b62eb28fa73
```

> `docker network ls`

```
NETWORK ID          NAME                DRIVER
5ba18807737b        dns-test            bridge              
4b737d016c3a        bridge              bridge              
90d198d92b1f        none                null                
29af26a1fcd2        host                host  
```

> `docker run -d --net dns-test --name dns-test-app rickfast/oreilly-dns-test`

```
8e021b6f420b4b40b1f97a5b9d62580c65980bc494284a35dac4570fc7d71b77
```

> `docker run alpine wget -qO- dns-test-app:4567` - bad address

```
wget: bad address 'dns-test-app:4567'
```

> `docker run --net dns-test alpine wget -qO- dns-test-app:4567`

```
You found me! My IP is 172.18.0.2
```

> `docker kill dns-test-app`

> `docker rm dns-test-app`

> `docker run -d --net dns-test --name dns-test-app --net-alias dns-alias rickfast/oreilly-dns-test`

```
fcb874b1d6a32c4b1c93a8c7d735c412fc7b54d0240e6b297527d6896fe1a44c
```

> `docker run --net dns-test alpine wget -qO- dns-test-app:4567`

```
You found me! My IP is 172.18.0.2
```

> `docker run --net dns-test alpine wget -qO- dns-alias:4567`

```
You found me! My IP is 172.18.0.2
```
