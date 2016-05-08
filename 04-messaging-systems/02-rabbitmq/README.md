# RabbitMQ
https://www.rabbitmq.com/install-homebrew.html
https://www.youtube.com/watch?v=8mFsh1cwlsA

## add to path

vi ~/.bash_profile

## Launch RabbitMQ

Start RabbitMQ
```
To have launchd start rabbitmq at login:
  ln -sfv /usr/local/opt/rabbitmq/*.plist ~/Library/LaunchAgents
Then to load rabbitmq now:
  launchctl load ~/Library/LaunchAgents/homebrew.mxcl.rabbitmq.plist
```

Stop RabbitMQ
```
launchctl unload ~/Library/LaunchAgents/homebrew.mxcl.rabbitmq.plist
```

## RabbitMQ Management
http://localhost:15672
