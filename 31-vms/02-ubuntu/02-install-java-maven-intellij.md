# Install Java and Maven

## Install Java

https://www.digitalocean.com/community/tutorials/how-to-install-java-on-ubuntu-with-apt-get

```
sudo apt-get update
﻿sudo apt-get install default-jdk

java -version
```

## Install Maven

https://www.mkyong.com/maven/how-to-install-maven-in-ubuntu/

```
$ sudo apt-get install maven

$ mvn -version

```

> Maven repository .m2 folder

https://archerimagine.wordpress.com/2014/03/18/m2-is-not-visible-after-installing-maven-in-ubuntu/

```
$ mvn

﻿$ ls .m2/
repository
```

## Install Intellij

> download intellij at https://www.jetbrains.com/idea/download/#section=linux to `Downloads`

```
root@osboxes:/home/osboxes/intellij# cp ../../Downloads/ideaIC-2016.1.3.tar.gz .
root@osboxes:/home/osboxes/intellij# tar xfz ideaIC-2016.1.3.tar.gz 
root@osboxes:/home/osboxes/intellij# cd idea-IC-145.1617.8/bin/
root@osboxes:/home/osboxes/intellij/idea-IC-145.1617.8/bin# ./idea.sh
```
