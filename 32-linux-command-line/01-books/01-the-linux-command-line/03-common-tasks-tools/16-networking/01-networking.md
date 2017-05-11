# Networking

> Commands

- ping - Send an ICMP ECHO_REQUEST to network hosts
- traceroute - Print the route packets trace to a network host
- netstat - Print network connections, routing tables, interface statistics, msquerade connections
- ftp - Internet file transfer program
- lftp - An improved Internet file transfer program
- wget - Non-interactive network downloader
- ssh - OpenSSH SSH client (remote login program)
- scp - Secure copy (remote file copy program)
- sftp - Secure file transfer program

*should know following*

- IP ()Internet protocol) address
- Host and domain name
- URI (uniform resource Identifier)

## Examining and Monitoring a Network

### ping - Send a Special Packet to a Network Host

> see if we can reach http://www.linuxcommand.org/

```
$ ping linuxcommand.org
PING linuxcommand.org (216.34.181.97) 56(84) bytes of data.
64 bytes from vhost.sourceforge.net (216.34.181.97): icmp_seq=1 ttl=63 time=88.0 ms
64 bytes from vhost.sourceforge.net (216.34.181.97): icmp_seq=2 ttl=63 time=63.0 ms
64 bytes from vhost.sourceforge.net (216.34.181.97): icmp_seq=3 ttl=63 time=69.3 ms
^C
--- linuxcommand.org ping statistics ---
4 packets transmitted, 4 received, 0% packet loss, time 3018ms
rtt min/avg/max/mdev = 63.058/71.350/88.025/9.897 ms
```
