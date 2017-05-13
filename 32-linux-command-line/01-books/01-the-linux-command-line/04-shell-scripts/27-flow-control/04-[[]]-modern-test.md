# Modern Version of test

https://www.safaribooksonline.com/library/view/the-linux-command/9781593273897/ch27s04.html

> Compound command is an enhanced replacement for `test`

```
[[ expression ]]
```

`expression` evaluates to either a true of false result.
`[[]]` command is very similar to test.

```
string1 =~ regex
```

it returns true if string1 is matched by the extended regex

## Example - verify constant contains an integer

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

*regex - `^-?[0-9]+$`*

- `^` - begin with
- `-?` - optional `-` because it could be negative
- `[0-9]` - any character from 0 to 9 range
- `+` - match one or more
- `$` - the end of line

> Test

```
$ bash foo.sh 
INT is negative.
INT is odd.
```
