# local development environment
https://www.youtube.com/watch?v=PmOMc4zfCSw

## create web server
1, open `Vagrantfile` under `vagrant` folder
2, uncomment following line and change the ip to "55.55.55.5"
```
  config.vm.network "private_network", ip: "55.55.55.5"
```
2.1, go to http://55.55.55.5/ - result: error unable to reach
3, $ vagrant reload
4, $ vagrant ssh
5, ~$ sudo apt-get update
6, ~$ sudo apt-get install nginx
7, ~$ sudo service nginx start
7.1, go to http://55.55.55.5/ - result: `Welcome to nginx!`
8, ~$ exit

### create domain name
9, $ atom /etc/hosts
10, add following to end of the `hosts` file
```
55.55.55.5 myawesomesite.com
```
11, save it. may need to enter password
12, go to http://myawesomesite.com/ - result: `Welcome to nginx!`
