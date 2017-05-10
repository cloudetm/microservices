# Options

## `-p` option

> foo.sh

```
#!/bin/bash

# read-single: read multiple values into default variable

read -p "Enter one or more values > "

echo "REPLY = '$REPLY'"
```

> Test

```
$ bash foo.sh
Enter one or more values > a b c d e
```

## `-t` and `-s` options

> foo.sh

```
#!/bin/bash

# read-secret: input a secret passphrase

if read -t 10 -sp "Enter secret passphrase > " secret_pass; then
  echo -e "\nSecret passphrase = '$secret_pass'"
else
  echo -e "\nInput timed out" >&2
  exit 1
fi
```

If the entry is not completed within the specified time, the script exits with an error.

> Test

```
$ bash foo.sh
Enter secret passphrase > 
Secret passphrase = '123abc'

$ bash foo.sh
Enter secret passphrase > 
Input timed out
```
