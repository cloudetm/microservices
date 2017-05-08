# cut - Remove Sections from Each Line of Files

The `cut` program extracts a section of text from a line and output the extracted section to standard output.

```
$ cat > distros.txt
Tom	10	12/07/2006
Dick	20	11/25/2008
Harry	30	06/19/2008

$ cat -A distros.txt 
Tom^I10^I12/07/2006$
Dick^I20^I11/25/2008$
Harry^I30^I06/19/2008$
```

> `-f` option to extract a field

```
$ cut -f 3 distros.txt 
12/07/2006
11/25/2008
06/19/2008

$ cut -f 2 distros.txt 
10
20
30
```

> `-c` extract character position 7 through 10

```
$ cut -f 3 distros.txt | cut -c 7-10
2006
2008
2008
```

> `-d` specify the colon character as the field delimiter

```
$ cat /etc/passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
sync:x:5:0:sync:/sbin:/bin/sync
shutdown:x:6:0:shutdown:/sbin:/sbin/shutdown
halt:x:7:0:halt:/sbin:/sbin/halt
mail:x:8:12:mail:/var/spool/mail:/sbin/nologin
operator:x:11:0:operator:/root:/sbin/nologin
games:x:12:100:games:/usr/games:/sbin/nologin
ftp:x:14:50:FTP User:/var/ftp:/sbin/nologin
nobody:x:99:99:Nobody:/:/sbin/nologin
systemd-bus-proxy:x:999:998:systemd Bus Proxy:/:/sbin/nologin
systemd-network:x:192:192:systemd Network Management:/:/sbin/nologin
dbus:x:81:81:System message bus:/:/sbin/nologin
polkitd:x:998:997:User for polkitd:/:/sbin/nologin
rpc:x:32:32:Rpcbind Daemon:/var/lib/rpcbind:/sbin/nologin
tss:x:59:59:Account used by the trousers package to sandbox the tcsd daemon:/dev/null:/sbin/nologin
rpcuser:x:29:29:RPC Service User:/var/lib/nfs:/sbin/nologin
nfsnobody:x:65534:65534:Anonymous NFS User:/var/lib/nfs:/sbin/nologin
sshd:x:74:74:Privilege-separated SSH:/var/empty/sshd:/sbin/nologin
postfix:x:89:89::/var/spool/postfix:/sbin/nologin
chrony:x:997:995::/var/lib/chrony:/sbin/nologin
vagrant:x:1000:1000:vagrant:/home/vagrant:/bin/bash

$ cut -d ':' -f 1 /etc/passwd | head
root
bin
daemon
adm
lp
sync
shutdown
halt
mail
operator
```
