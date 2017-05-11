# `REPLY`

`REPLY` will be assigned all the input if no variables are listed after the `read`

> foo.sh

```
#!/bin/bash

# read-single: read multiple values into default variable

echo -n "Enter one or more values > "
read

echo "REPLY = '$REPLY'"
```

> Test

```
$ bash foo.sh
Enter one or more values > a b c d e
REPLY = 'a b c d e'
```
