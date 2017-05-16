# Flow Control: Branching with case

`case` - the bash multiple-choice compound command

```
case word in
        [pattern [| pattern]...) commands ;;]...
esac
```

## case-menu

> foo.sh

```
#!/bin/bash

# case-menu: a menu driven system information program

clear
echo "
Please Select:

1. Display System Information
2. Display Disk Space
3. Display Home Space Utilization
0. Quit
"

read -p "Enter selection [0-3] > "

case $REPLY in
  0) echo "Program terminated."
     exit
     ;;
  1) echo "Hostname: $HOSTNAME"
     uptime
     ;;
  2) df -h
     ;;
  3) if [[ $(id -u) -eq 0 ]]; then
       echo "Home Space Utilization (All Users)"
       du -sh /home/*
     else
       echo "Home Space Utilization ($USER)"
       du -sh $HOME
     fi
     ;;
  *) echo "Invalid entry" >&2
     exit 1
     ;;
esac
```

> Test

```
$ bash foo.sh
Please Select:

1. Display System Information
2. Display Disk Space
3. Display Home Space Utilization
0. Quit

Enter selection [0-3] > 1
Hostname: localhost.localdomain
 15:17:43 up  9:29,  1 user,  load average: 0.00, 0.01, 0.05
```

## Patterns

> `case` Pattern

| Pattern      | Description |
|--------------|-------------|
| a)           | Matches if word equals a |
| [[:alpha:]]) | Matches if word is a single alphabetic character |
| ???)         | Matches if word is exactly three characters longs |
| *.txt)       | Matches if word ends with the characters .txt |
| *)           | Matches any value of word. default case |


> foo.sh

```
#!/bin/bash

read -p "enter word > "

case $REPLY in
  [[:alpha:]])  echo "is a single alphabetic character.";;
  [ABC][0-9])   echo "is A, B, or C followed by a digit.";;
  ???)          echo "is three characters long.";;
  *.txt)        echo "is a word ending in '.txt'";;
  *)            echo "is something else.";;
esac
```

> Test

```
$ bash foo.sh
enter word > a
is a single alphabetic character.
$ bash foo.sh
enter word > foo.txt
is a word ending in '.txt'
```
