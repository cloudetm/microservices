# Integer Expressions

> test Integer Expressions

| Expression            | Is true if... |
|-----------------------|---------------|
| integer1 -eq integer2 | integer1 is equal to integer2 |
| integer1 -ne integer2 | integer1 is not equal to integer2 |
| integer1 -le integer2 | integer1 is less than or equal to integer2 |
| integer1 -lt integer2 | integer1 is less than integer2 |
| integer1 -ge integer2 | integer1 is greater than or equal to integer2 |
| integer1 -gt integer2 | integer1 is greater than integer2 |

## Example

> foo.sh

```
#!/bin/bash

# test-integer: evaluate the value of an integer

INT=-5

if [ -z "$INT" ]; then
  echo "INT is empty." >&2
  exit 1
fi

if [ $INT -eq 0 ]; then
  echo "INT is zero."
else
  if [ $INT -lt 0 ]; then
    echo "INT is negative."
  else
    echo "INT is positive."
  fi
  if [ $((INT % 2)) -eq 0 ]; then
    echo "INT is even."
  else
    echo "INT is odd."
  fi
fi
```

> Test

```
$ bash foo.sh
INT is negative.
INT is odd.
```
