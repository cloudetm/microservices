#!/bin/sh
hostname SensuServer
apt-get install rabbitmq-server redis-server -y
cd /sbin
su - -c 'rabbitmq-plugins enable rabbitmq_management'
service rabbitmq-server restart
rabbitmqctl add_vhost /sensu
rabbitmqctl add_user sensu secret
rabbitmqctl set_permissions -p /sensu sensu ".*" ".*" ".*"
wget -q http://repos.sensuapp.org/apt/pubkey.gpg
apt-key add pubkey.gpg
echo "deb     http://repos.sensuapp.org/apt sensu main" | sudo tee /etc/apt/sources.list.d/sensu.list
apt-get update
apt-get install sensu -y
cat <<EOT >> /etc/sensu/config.json
{
	"rabbitmq": {
	  "host": "localhost",
	  "vhost": "/sensu",
	  "user": "sensu",
	  "password": "secret"
	},
	"redis": {
	  "host": "localhost"
	},
	"api": {
      "port": 4567
	}
}
EOT
/etc/init.d/sensu-server start
/etc/init.d/sensu-api start
