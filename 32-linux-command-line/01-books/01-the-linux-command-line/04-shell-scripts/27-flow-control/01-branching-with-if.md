# Branching with if

## Example
 
> branching-with-if.sh

```
$ cat branching-with-if.sh 
#!/bin/bash

x=5

if [ $x = 5 ]; then
  echo "x equals 5."
else
  echo "x does not equal 5."
fi
```

> Test

```
$ bash branching-with-if.sh 
x equals 5.
```
