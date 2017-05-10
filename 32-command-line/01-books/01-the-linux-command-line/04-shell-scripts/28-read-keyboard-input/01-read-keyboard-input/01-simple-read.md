# Simple `read`

> foo.sh - `read`

```
#!/bin/bash

# test-integer: evaluate the value of an integer

echo -n "Please enter an integer -> "
read int

if [[ "$int" =~ ^-?[0-9]+$ ]]; then
  if (($int == 0)); then
    echo "$int is zero."
  else
    if (($int < 0)); then
      echo "$int is negative."
    else
      echo "$int is positive."
    fi
    if (( (($int % 2)) == 0 )); then
      echo "$int is even."
    else
      echo "$int is odd."
    fi
  fi
else
  echo "$int is not an integer." >&2
  exit 1
fi
```

- use `echo` with the `-n` option to display a prompt
- use `read` to input a value for the variable int

> Test

```
$ bash foo.sh
Please enter an integer -> 5
5 is positive.
5 is odd.
```
