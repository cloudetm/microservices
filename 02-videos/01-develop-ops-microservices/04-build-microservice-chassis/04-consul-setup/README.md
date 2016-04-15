# Consul Setup

```
1, go to this folder has Vagrantfile
1.1, $ vagrant status
1.2, $ vagrant destroy if you want to destroy it and start
2, $ vagrant up <- it shows up in VirualBox Manager
3, go to http://192.168.33.10:8500/ui/#/dc1/services
4, $ vagrant status
Current machine states:
services                  running (virtualbox)
5, $ docker run -p 8300:8300 -p 8301:8301/udp -p 8302:8302 -p 8302:8302/udp -p 8400:8400 -p 8500:8500 progrium/consul -advertise 192.168.99.100 -join 192.168.33.10
==> WARNING: It is highly recommended to set GOMAXPROCS higher than 1
==> Starting Consul agent...
==> Starting Consul agent RPC...
==> Joining cluster...
    Join completed. Synced with 1 initial agents
==> Consul agent running!
         Node name: 'a5d243cb21fd'
        Datacenter: 'dc1'
            Server: false (bootstrap: false)
       Client Addr: 0.0.0.0 (HTTP: 8500, HTTPS: -1, DNS: 53, RPC: 8400)
      Cluster Addr: 192.168.99.100 (LAN: 8301, WAN: 8302)
    Gossip encrypt: false, RPC-TLS: false, TLS-Incoming: false
             Atlas: <disabled>
==> Log data will now stream in as it occurs:
    2016/04/15 00:53:10 [INFO] serf: EventMemberJoin: a5d243cb21fd 192.168.99.100
    2016/04/15 00:53:10 [INFO] agent: (LAN) joining: [192.168.33.10]
    2016/04/15 00:53:10 [INFO] serf: EventMemberJoin: 70c0e9db69d9 192.168.33.10
    2016/04/15 00:53:10 [INFO] agent: (LAN) joined: 1 Err: <nil>
    2016/04/15 00:53:10 [ERR] agent: failed to sync remote state: No known Consul servers
    2016/04/15 00:53:10 [INFO] consul: adding server 70c0e9db69d9 (Addr: 192.168.33.10:8300) (DC: dc1)
6, go to http://192.168.33.10:8500/ui/#/dc1/nodes - you will see two nodes
```
