# Separating Input Fields with IFS

The shell performs word splitting on the input provided to read.

> grep a user `vagrant` from /etc/passwd

```
$ grep "vagrant:" /etc/passwd
vagrant:x:1000:1000:vagrant:/home/vagrant:/bin/bash
```

> foo.sh

```
#!/bin/bash

# read-ifs: read fields from a file

FILE=/etc/passwd

read -p "Enter a username > " user_name

file_info=$(grep "^$user_name:" $FILE)

if [ -n "$file_info" ]; then
  IFS=":" read user pw uid gid name home shell <<< "$file_info"
  echo "User =      '$user'"
  echo "UID =        '$uid'"
  echo "GID =        '$gid'"
  echo "Full Name = '$name'"
  echo "Home Dir. = '$home'"
  echo "Shell =    '$shell'"
else
  echo "No such user '$user_name'" >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh
Enter a username > vagrant
User =      'vagrant'
UID =        '1000'
GID =        '1000'
Full Name = 'vagrant'
Home Dir. = '/home/vagrant'
Shell =    '/bin/bash'
```
