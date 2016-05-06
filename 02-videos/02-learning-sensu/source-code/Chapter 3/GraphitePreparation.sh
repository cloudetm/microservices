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
cat <<EOT > /etc/sensu/config.json
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
      "host": "localhost",
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
    ],
    "environment": "Production"
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

apt-get install ruby ruby-dev make -y
gem install sensu-plugins-pagerduty
cat <<EOT >> /etc/sensu/conf.d/handler_tcp.json
{
  "handlers": {
    "handler_tcp": {
      "type": "tcp",
      "mutator": "mutator_pretty",
      "socket": {
        "host": "localhost",
        "port": 6000
      }
    }
  }
}
EOT

cat <<EOT >> /etc/sensu/conf.d/handler_pagerduty.json
{
  "pagerduty": {
    "api_key": "1703026fefbe4579a7225507f094c416"
  },
  "handlers": {
    "handler_pagerduty": {
      "type": "pipe",
      "command": "handler-pagerduty.rb",
      "severities": [
        "critical",
        "ok"
      ],
      "filter": "filter_production"
    }
  }
}
EOT

cat <<EOT > /etc/sensu/conf.d/check_file.json
{
  "checks": {
    "check_file": {
      "command": "/etc/sensu/plugins/check_file.sh",
      "subscribers": [
        "Production"
      ],
      "interval": 5,
      "handler": "handler_tcp",
      "occurrences": 1,
      "refresh": 1
    }
  }
}
EOT

# Set up Filters
cat <<EOT > /etc/sensu/conf.d/filter_production.json
{
  "filters": {
    "filter_production": {
      "attributes": {
        "client": {
          "environment": "Production"
        }
      }
    }
  }
}
EOT

# Set up Mutator
cat <<EOT > /etc/sensu/conf.d/mutator_pretty.json
{
  "mutator_pretty": {
    "command": "/etc/sensu/mutators/prettymutator.rb"
  }
}
EOT

cat <<EOT > /etc/sensu/mutators/prettymutator.rb
#!/usr/bin/env ruby

require 'rubygems'
require 'json'

puts JSON.pretty_generate(JSON.parse(STDIN.read))
EOT
service sensu-client restart && service sensu-api restart && service sensu-server restart
