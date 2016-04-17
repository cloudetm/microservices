# Sensu Dashboard

```
~# vi /etc/sensu/conf.d/client.json
~# cat /etc/sensu/conf.d/client.json
{
  "client": {
	"name": "SensuServer",
	"address": "10.0.2.15",
	"subscriptions": [
  	"Production"
	]
  }
}
~# service sensu-client start
go to https://www.uchiwa.io/#/download to copy download link
~# wget http://dl.bintray.com/palourde/uchiwa/uchiwa_0.14.2-1_amd64.deb
~# dpkg -i uchiwa_0.14.2-1_amd64.deb
~# vi /etc/sensu/uchiwa.json
~# cat /etc/sensu/uchiwa.json
{
  "sensu": [
	{
  	"name": "SensuServer",
  	"host": "localhost",
  	"port": 4567,
  	"ssl": false,
  	"path": "",
  	"user": "sensu",
  	"pass": "secret",
  	"timeout": 5
	}
  ],
  "uchiwa": {
	"host": "0.0.0.0",
	"port": 3000,
	"refresh": 5
  }
}
~# service uchiwa start
uchiwa started.
go to http://10.0.2.15:3000 to view uchiwa web-ui
click “CLIENTS” on the left side, “SensuServer” is shown
~# service sensu-client stop <- cause an event, “No keepaline sent from client for 180 seconds”
~# service sensu-client start
```
