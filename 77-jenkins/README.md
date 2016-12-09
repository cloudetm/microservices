# jenkins

## localhost
http://localhost:8080/

> start jenkins

sudo launchctl load /Library/LaunchDaemons/org.jenkins-ci.plist

> stop jenkins

sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist

## install centos in virtualbox

http://www.osboxes.org/centos/

## files location

> `~/Documents/vms/`

> password: osboxes.org

## uninstall

http://stackoverflow.com/questions/11608996/how-to-uninstall-jenkins

```
sudo launchctl unload /Library/LaunchDaemons/org.jenkins-ci.plist
sudo rm /Library/LaunchDaemons/org.jenkins-ci.plist
sudo rm -rf /Applications/Jenkins "/Library/Application Support/Jenkins" /Library/Documentation/Jenkins
sudo rm -rf /Users/Shared/Jenkins
sudo dscl . -delete /Users/jenkins
sudo dscl . -delete /Groups/jenkins
```

## Books

https://www.safaribooksonline.com/library/view/mastering-jenkins/9781784390891/

https://www.safaribooksonline.com/library/view/jenkins-essentials/9781783553471/

## references

https://www.youtube.com/watch?v=Doi1IJ3bnkY

http://www.cimgf.com/2015/05/26/setting-up-jenkins-ci-on-a-mac-2/


