# (()) - Designed for Integers

`(())` is used to perform arithmetic truth tests.

> arithmetic truth test results - true if the result of the evaluation is non-zero

```
$ if ((1)); then echo "It is true."; fi
It is true.

$ if ((0)); then echo "It is true."; fi
```

## Example

> foo.sh

```
#!/bin/bash

# test-integer2: evaluate the value of an integer

INT=-5

if [[ "$INT" =~ ^-?[0-9]+$ ]]; then
  if ((INT == 0)); then
    echo "INT is zero."
  else
    if ((INT < 0)); then
      echo "INT is negative."
    else
      echo "INT is positive."
    fi
    if (( ((INT % 2)) == 0 )); then
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
