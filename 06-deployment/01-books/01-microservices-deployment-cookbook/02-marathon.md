# Marathon - Mesos cluster

Create Mesos cluster
docker-compose -f docker-compose-mesos.yml up

> Launch Marathon

http://192.168.99.100:8080/

> Create Application

```
1, Under General tab
  ID: geolocation
  CPUs: 1
  Memory: 128
  Disk Space: 1024
  Instances: 1
2, Docker Container tab
  Image: pwillhan/geolocation
  Network: Bridged
3, Click "Create Application"
```

> Modify Port

*Part 1*

```
1, Under Ports tab
  Container Port: 8080
  Protocol: tcp
2, Click "Change and deploy configuration"
```
  
*Part 2*

```
1, Click top right JSON mode to modify json
  servicePort: 8899
2, Click "Change and deploy configuration"
```

> Get IP and Port

```
1, Click the applicaiton to go into the application instance
2, under Instances, ID column, you will see something like 192.168.99.100:31642
```

> Test

curl http://192.168.99.100:31934/contacts
