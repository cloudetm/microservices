# User Defined Bridge Networks

> `docker network create --driver bridge my-network` - define a new network `my-network`

```
49ce4f9186bf6285b72067c9d62a9b242f690b4926335059beef1fb78d95c5ea
```

> `docker network ls`

```
NETWORK ID          NAME                DRIVER
49ce4f9186bf        my-network          bridge              
4b737d016c3a        bridge              bridge              
90d198d92b1f        none                null                
29af26a1fcd2        host                host  
```

> `docker run -d -P --net my-network --name hello rickfast/hello-oreilly-http` - run container with defined network

```
8cf1b624659c515f5b3aafbafcc2753f048f6c41df8b41113637cbfaeacf2a03
```

> `docker inspect hello` - inspect container

```
"Networks": {
                "my-network": {
                    "IPAMConfig": null,
                    "Links": null,
```

> `docker network rm 49ce4f9186bf` - delete defined network

