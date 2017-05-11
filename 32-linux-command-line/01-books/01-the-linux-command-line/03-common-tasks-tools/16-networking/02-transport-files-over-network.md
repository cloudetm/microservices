# Transporting Files over a Network

## ftp - Transfer Files with the File Transfer Protocol

FTP is not secure, because it sends account names and passwords in cleartext.

## wget - Non-interactive Network Downloader

> install wget - centos

```
# yum install wget
```

> download the first page of http://www.linuxcommand.org/

```
# wget http://linuxcommand.org/index.php
--2017-05-01 06:01:47--  http://linuxcommand.org/index.php
Resolving linuxcommand.org (linuxcommand.org)... 216.34.181.97
Connecting to linuxcommand.org (linuxcommand.org)|216.34.181.97|:80... connected.
HTTP request sent, awaiting response... 200 OK
Length: 3808 (3.7K) [text/html]
Saving to: ‘index.php’

100%[================================================================>] 3,808       --.-K/s   in 0s      

2017-05-01 06:01:47 (358 MB/s) - ‘index.php’ saved [3808/3808]
```

