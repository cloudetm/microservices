# Modern Version of test

> Compound command

```
[[ expression ]]
```

`expression` evaluates to either a true of false result.
`[[]]` command is very similar to test.

```
string1 =~ regex
```

## Example

> foo.sh

```
#!/bin/bash

# test-integer2: evaluate the value of an integer

INT=-5

if [[ "$INT" =~ ^-?[0-9]+$ ]]; then
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
else
  echo "INT is not an integer." >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh 
INT is negative.
INT is odd.
```
