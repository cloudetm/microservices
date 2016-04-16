#!/bin/sh

# Install Sensu, RabbitMQ, Redis
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

# Configure Sensu Client
IP=$(hostname -I)
cat <<EOT >> /etc/sensu/conf.d/client.json
{
  "client": {
    "name": "SensuServer",
    "address": "$IP",
    "subscriptions": [
      "Production"
    ]
  }
}
EOT
service sensu-client start

# Configure Uchiwa
wget http://dl.bintray.com/palourde/uchiwa/uchiwa_0.10.3-1_amd64.deb
dpkg -i uchiwa_0.10.3-1_amd64.deb
cat <<EOT > /etc/sensu/uchiwa.json
{
  "sensu": [
    {
      "name": "SensuServer",
      "host": "localhost",
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
#####################
#Configure Checks
#####################

# The check_file definition
cat <<EOT > /etc/sensu/conf.d/check_file.json
{
  "checks": {
    "check_file": {
      "command": "/etc/sensu/plugins/check_file.sh",
      "subscribers": [
        "Production"
      ],
      "interval": 5
    }
  }
}
EOT

# The check_file check
cat <<EOT > /etc/sensu/plugins/check_file.sh
#!/bin/sh
if [ -f "/tmp/file" ]
then
  echo "file exists!"
  exit 0
else
  echo "file is missing!"
  exit 2
fi
EOT

# Make check_file.sh executable
chmod +x /etc/sensu/plugins/check_file.sh

# Create testfile
touch /tmp/file

# Install Nagios Plugins
apt-get install Nagios-plugins -y

# Add check definition for Nagios check_disk check.
cat <<EOT > /etc/sensu/conf.d/check_disk.json
{
  "checks": {
    "check_disk": {
      "command": "/usr/lib/nagios/plugins/check_disk -w 25% -c 10%",
      "subscribers": [
        "Production"
      ],
      "interval": 5
    }
  }
}
EOT

# Restart Sensu Services
service sensu-server restart && service sensu-client restart && service sensu-api restart
