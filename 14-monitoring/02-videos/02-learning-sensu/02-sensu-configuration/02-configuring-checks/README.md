# Configuring Checks

```
# cd /etc/sensu/conf.d/
# vi check_file.json
# cat check_file.json
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
# cd /etc/sensu/plugins/
# vi check_file.sh
# cat check_file.sh
#!/bin/sh
if [ -f "/tmp/file" ]
then
  echo "file exists!"
  exit 0
else
  echo "file is missing!"
  exit 2
fi
# chmod +x /etc/sensu/plugins/check_file.sh <- make check_file.sh executable
# service sensu-server restart && service sensu-client restart && service sensu-api restart
Go to http://10.0.2.15:3000/#/events - Output: “file is missing!”
# touch /tmp/file
http://10.0.2.15:3000/#/events turns green after 5 seconds
# apt-get install Nagios-plugins -y
# cd /usr/lib/nagios/plugins/
# ls
https://www.monitoring-plugins.org/
# vi /etc/sensu/conf.d/check_disk.json
# cat /etc/sensu/conf.d/check_disk.json
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
# service sensu-server restart && service sensu-client restart && service sensu-api restart
Go to http://10.0.2.15:3000/#/checks <- will see the checks that we just created
```
