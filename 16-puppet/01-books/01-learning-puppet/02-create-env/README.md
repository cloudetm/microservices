# create learning environment

checkout 11-vagrant/README.md

## puppet installation
https://www.youtube.com/watch?v=j4331uoHIR8

https://www.youtube.com/watch?v=sPRjaxUoTeQ

~$ sudo apt-get update

~$ lsb_release -d

~$ wget https://apt.puppetlabs.com/puppetlabs-release-pc1-trusty.deb

~$ sudo dpkg -i puppetlabs-release-pc1-trusty.deb

~$ cd /etc/apt/sources.list.d/

/etc/apt/sources.list.d$ ls

/etc/apt/sources.list.d$ sudo apt-get update

~$ sudo apt-get install puppet-agent puppetserver

~$ which puppet

~$ sudo puppet resource service puppetserver ensure=running

~$ sudo puppet resource service puppetserver enable=true

~$ puppet --version