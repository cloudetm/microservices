# ubuntu

## install ubuntu in virtualbox

http://www.osboxes.org/ubuntu/

## files location

> `~/Documents/vms/`

> password: osboxes.org

## Version

lsb_release -a

## Install Java

http://tecadmin.net/install-oracle-java-8-jdk-8-ubuntu-via-ppa/

> `sudo -i` run as root

> `﻿nano /etc/environment`

> append following

```
﻿JAVA_HOME="/usr/lib/jvm/java-8-oracle"
```

> `﻿source /etc/environment`

> `﻿echo $JAVA_HOME`

## Install Intellij

> download intellij at ﻿https://www.jetbrains.com/idea/download/#section=linux to `Downloads`

```
root@osboxes:/home/osboxes/Documents/intellij# cp ../../Downloads/ideaIC-2016.1.3.tar.gz .
root@osboxes:/home/osboxes/Documents/intellij# tar xfz ideaIC-2016.1.3.tar.gz 
root@osboxes:/home/osboxes/Documents/intellij# cd idea-IC-145.1617.8/bin/
root@osboxes:/home/osboxes/Documents/intellij/idea-IC-145.1617.8/bin# ./idea.sh
```

## Install git

> `﻿apt install git`

### change resolution
https://www.youtube.com/watch?v=t36wXUu1UtQ

Launch ubuntu, click "Devices", "Insert Guest Additions CD image"

## run as root

- `CTRL + ALT + T` to open terminal
- type `sudo -i` and press enter
- enter the password
