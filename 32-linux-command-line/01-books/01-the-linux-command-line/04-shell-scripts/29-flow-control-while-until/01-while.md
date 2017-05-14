# while

> while command

```
while commands; do commands; done
```

> display five numbers in sequential order from 1 to 5

> foo.sh

```
#!/bin/bash

# while-count: display a series of numbers

count=1

while [ $count -le 5 ]; do
  echo $count
  count=$((count + 1))
done
echo "Finished."
```

> Test

```
$ bash foo.sh 
1
2
3
4
5
Finished.
```

## menu - while loop

> foo.sh

```
#!/bin/bash

# while-menu: a menu driven system information program

DELAY=3 # Number of seconds to display results

while [[ $REPLY != 0 ]]; do
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
    fi
    if [[ $REPLY == 2 ]]; then
      df -h
      sleep $DELAY
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
