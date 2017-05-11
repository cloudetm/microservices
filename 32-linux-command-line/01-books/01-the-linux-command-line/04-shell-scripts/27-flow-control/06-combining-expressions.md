# Combining Expressions

> Logical operations

| Operation | test | [[]] and (()) |
|-----------|--------------------|
| AND       | -a   | && |
| OR        | -o   | || |
| NOT       | !    | !  |

## Examples

### `&&` - and

> foo.sh

```
#!/bin/bash

# test-integer3: determine if an integer is within a specified range of values

MIN_VAL=1
MAX_VAL=100

INT=50

if [[ "$INT" =~ ^-?[0-9]+$ ]]; then
  if [[ INT -ge MIN_VAL && INT -le MAX_VAL ]]; then
    echo "$INT is within $MIN_VAL to $MAX_VAL."
  else
    echo "$INT is out of range."
  fi
else
  echo "INT is not an integer." >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh
50 is within 1 to 100.
```

### `!` - not

> foo.sh

```
#!/bin/bash

# test-integer3: determine if an integer is within a specified range of values

MIN_VAL=1
MAX_VAL=100

INT=50

if [[ "$INT" =~ ^-?[0-9]+$ ]]; then
  if [[ ! INT -ge MIN_VAL && INT -le MAX_VAL ]]; then
    echo "$INT is outside $MIN_VAL to $MAX_VAL."
  else
    echo "$INT is in range."
  fi
else
  echo "INT is not an integer." >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh
50 is in range.
```
