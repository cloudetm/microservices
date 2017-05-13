# Common regex

## `=~`

The right side is considered an extended regular expression.
If the left side matches, the operator returns 0, and 1 otherwise

> foo.sh

```
#!/bin/bash

if [[ "123" =~ ^-?[0-9]+$ ]]; then
  echo "pass"
else
  echo "fail"
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
pass

```
