# Docker Networking

> `docker network ls`

```
NETWORK ID          NAME                DRIVER
4b737d016c3a        bridge              bridge              
90d198d92b1f        none                null                
29af26a1fcd2        host                host  
```

> `docker-machine ssh default` - ssh into default container with bridge network

> `docker@default:~$ ifconfig` 

```
docker0   Link encap:Ethernet  HWaddr 02:42:15:E4:68:DB  
          inet addr:172.17.0.1  Bcast:0.0.0.0  Mask:255.255.0.0
          inet6 addr: fe80::42:15ff:fee4:68db/64 Scope:Link
          UP BROADCAST MULTICAST  MTU:1500  Metric:1
          RX packets:91554 errors:0 dropped:0 overruns:0 frame:0
          TX packets:125026 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:3853993 (3.6 MiB)  TX bytes:357999657 (341.4 MiB)
```

> `docker run -d -P --net none --name no-network-app rickfast/hello-oreilly-http`

> `docker exec -i -t no-network-app /bin/sh` - go into none-network interactive mode

> `/ # ifconfig` - only local loopback is available

```
lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1%32662/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)
```

> `docker run -d -P --net host --name host-network-app rickfast/hello-oreilly-http` - go into the host-network

> `docker exec -i -t host-network-app /bin/sh` - go into the interactive mode

> `/ # ifconfig`

```
docker0   Link encap:Ethernet  HWaddr 02:42:15:E4:68:DB  
          inet addr:172.17.0.1  Bcast:0.0.0.0  Mask:255.255.0.0
          inet6 addr: fe80::42:15ff:fee4:68db%32555/64 Scope:Link
          UP BROADCAST MULTICAST  MTU:1500  Metric:1
          RX packets:91554 errors:0 dropped:0 overruns:0 frame:0
          TX packets:125026 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:3853993 (3.6 MiB)  TX bytes:357999657 (341.4 MiB)
```

> `docker run -P --net host --name host-network-apps2 rickfast/hello-oreilly-http` - run the same image using host-network results conflict

```
[2016-05-24 06:03:18] INFO  WEBrick 1.3.1
[2016-05-24 06:03:18] INFO  ruby 2.2.4 (2015-12-16) [x86_64-linux-musl]
== Someone is already performing on port 4567!
/usr/lib/ruby/2.2.0/socket.rb:206:in `bind': Address in use - bind(2) for 0.0.0.0:4567 (Errno::EADDRINUSE)
	from /usr/lib/ruby/2.2.0/socket.rb:206:in `listen'
	from /usr/lib/ruby/2.2.0/socket.rb:461:in `block in tcp_server_sockets'
	from /usr/lib/ruby/2.2.0/socket.rb:232:in `each'
	from /usr/lib/ruby/2.2.0/socket.rb:232:in `foreach'
	from /usr/lib/ruby/2.2.0/socket.rb:459:in `tcp_server_sockets'
	from /usr/lib/ruby/2.2.0/webrick/utils.rb:70:in `create_listeners'
	from /usr/lib/ruby/2.2.0/webrick/server.rb:133:in `listen'
	from /usr/lib/ruby/2.2.0/webrick/server.rb:114:in `initialize'
	from /usr/lib/ruby/2.2.0/webrick/httpserver.rb:45:in `initialize'
	from /usr/lib/ruby/gems/2.2.0/gems/rack-1.6.4/lib/rack/handler/webrick.rb:31:in `new'
	from /usr/lib/ruby/gems/2.2.0/gems/rack-1.6.4/lib/rack/handler/webrick.rb:31:in `run'
	from /usr/lib/ruby/gems/2.2.0/gems/sinatra-1.4.7/lib/sinatra/base.rb:1506:in `start_server'
	from /usr/lib/ruby/gems/2.2.0/gems/sinatra-1.4.7/lib/sinatra/base.rb:1444:in `run!'
	from /usr/lib/ruby/gems/2.2.0/gems/sinatra-1.4.7/lib/sinatra/main.rb:25:in `block in <module:Sinatra>'
```	
