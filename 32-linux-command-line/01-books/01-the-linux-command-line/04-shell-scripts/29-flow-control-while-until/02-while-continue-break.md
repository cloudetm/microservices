# Breaking out of a loop

`break` - the break command immediately terminates a loop
`continue` - the continue command causes the remainder of the loop to be skipped

## menu - while loop continue break

> foo.sh

```
#!/bin/bash

# while-menu: a menu driven system information program

DELAY=3 # Number of seconds to display results

while true; do
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
    if [[ $REPLY == 1 ]]; then
      echo "Hostname: $HOSTNAME"
      uptime
      sleep $DELAY
      continue
    fi
    if [[ $REPLY == 2 ]]; then
      df -h
      sleep $DELAY
      continue
    fi
    if [[ $REPLY == 3 ]]; then
      if [[ $(id -u) -eq 0 ]]; then
        echo "Home Space Utilization (All Users)"
        du -sh /home/*
      else
        echo "Home Space Utilization ($USER)"
        du -sh $HOME
      fi
      sleep $DELAY
      continue
    fi
    if [[ $REPLY == 0 ]]; then
      break
    fi
  else
    echo "Invalid entry."
    sleep $DELAY
  fi
done
echo "Program terminated."
```

> Test

```
$ bash foo.sh

    Please Select:

    1. Display System Information
    2. Display Disk Space
    3. Display Home Space Utilization
    0. Quit
  
Enter selection [0-3] > 0
Program terminated.
```
