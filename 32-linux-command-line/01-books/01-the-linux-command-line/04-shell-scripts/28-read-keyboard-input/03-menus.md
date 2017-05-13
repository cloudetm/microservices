# Menus

In menu-driven programs, the user is presented with a list of choices and is asked to choose one.

> foo.sh

```
#!/bin/bash

# read-menu: a menu driven system information program

clear
echo "
Please Select:

1. Display System Information
2. Display Disk Space
3. Display Home Space Utilization
0. Quit
"
read -p "Enter selection [0-3] > "

if [[ $REPLY =~ ^[0-3]$ ]]; then
  if [[ $REPLY == 0 ]]; then
    echo "Program terminated."
    exit
  fi
  if [[ $REPLY == 1 ]]; then
    echo "Hostname: $HOSTNAME"
    uptime
    exit
  fi
  if [[ $REPLY == 2 ]]; then
    df -h
    exit
  fi
  if [[ $REPLY == 3 ]]; then
    if [[ $(id -u) -eq 0 ]]; then
      echo "Home Space Utilization (All Users)"
      du -sh /home/*
    else
      echo "Home Space Utilization ($USER)"
      du -sh $HOME
    fi
    exit
  fi
else
  echo "Invalid entry." >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh
Please Select:

1. Display System Information
2. Display Disk Space
3. Display Home Space Utilization
0. Quit

Enter selection [0-3] > 3
Home Space Utilization (vagrant)
108K	/home/vagrant
```
