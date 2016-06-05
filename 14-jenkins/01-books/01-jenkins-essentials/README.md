# Jenkins Essentials

https://www.safaribooksonline.com/library/view/jenkins-essentials/9781783553471/

## centos

### osboxes - USE THIS

http://www.osboxes.org/centos/#centos-7-1-1511-vbox

> Hard disk: Use an existing virtual hard disk file

> password: osboxes.org

> Network - Wired: `On`

> `su - root`

> `yum update`

### Install Jenkins

#### Install Jenkins 1.6 - USE THIS

> use commands at http://pkg.jenkins-ci.org/redhat-stable/

> `sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo`

> `sudo rpm --import http://pkg.jenkins-ci.org/redhat-stable/jenkins-ci.org.key`

> `yum install jenkins`

> install java http://tecadmin.net/install-java-8-on-centos-rhel-and-fedora/

> `sudo service jenkins start/stop/restart`

> http://sharadchhetri.com/2013/06/03/how-to-set-java-environment-variables-in-linux-or-centos/

> `vi /root/.bash_profile`

```
export JAVA_HOME=/opt/jdk1.8.0_91
export JRE_HOME=/opt/jdk1.8.0_91/jre
export PATH=$PATH:/opt/jdk1.8.0_91/bin:/opt/jdk1.8.0_91/jre/bin
```

> `source /root/.bash_profile` - setting java variables

> `echo $JAVA_HOME`

### Install maven

> wget https://maven.apache.org/download.cgi

http://preilly.me/2013/05/10/how-to-install-maven-on-centos/

### Install git

> `yum install git`

> `git --version`

### Auto capture keyboard

> left `Command` key to release keyboard and mouse

### Tips And Tricks

https://wiki.centos.org/TipsAndTricks/BecomingRoot

#### Install Jenkins 2.7 and Java

https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Red+Hat+distributions

> `sudo service jenkins start/stop/restart/status`

#### Jenkins plugins

> select custom install and install all plugins

