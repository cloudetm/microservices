#!/bin/sh
wget -q http://repos.sensuapp.org/apt/pubkey.gpg
apt-key add pubkey.gpg
echo "deb     http://repos.sensuapp.org/apt sensu main" | sudo tee /etc/apt/sources.list.d/sensu.list
apt-get update
apt-get install sensu -y

cat <<EOT >> /etc/sensu/config.json
{
	"rabbitmq": {
	  "host": "RabbitMQ-369392502.us-west-2.elb.amazonaws.com",
	  "vhost": "/sensu",
	  "user": "sensu",
	  "password": "secret"
	},
	"redis": {
	  "host": "sensuredis.8egwey.ng.0001.usw2.cache.amazonaws.com"
	},
	"api": {
      "port": 4567
	}
}
EOT

/etc/init.d/sensu-server start
/etc/init.d/sensu-api start
# Configure Sensu Client
IP=$(hostname -I)
cat <<EOT >> /etc/sensu/conf.d/client.json
{
	"client": {
	  "name": "SensuServer-$(hostname)",
	  "address": "$IP",
	  "subscriptions": [
	    "Production"
	  ]
	}
}
EOT
service sensu-client start
wget http://dl.bintray.com/palourde/uchiwa/uchiwa_0.10.3-1_amd64.deb
dpkg -i uchiwa_0.10.3-1_amd64.deb
cat <<EOT > /etc/sensu/uchiwa.json
{
  "sensu": [
    {
      "name": "SensuServer",
      "host": "sensu-374559574.us-west-2.elb.amazonaws.com",
      "port": 4567,
      "ssl": false,
      "path": "",
      "user": "sensu",
      "pass": "sensu",
      "timeout": 5
    }
  ],
  "uchiwa": {
    "host": "0.0.0.0",
    "port": 3000,
    "interval": 5
  }
}

EOT
service uchiwa start
