# String Expressions

> test String Expressions

| Expression         | Is true if... |
|--------------------|---------------|
| string             | string is not null |
| -n string          | The length of string is greater than zero |
| -z string          | The length of string is zero |
| string1 = string2  | string1 and string2 are equal. Single or double |
| string1 == string2 | preferred equal signs |
| string1 != string2 | string1 and string2 are not equal |
| string1 > string2  | string1 sorts after strings2 |
| string1 < string2  | string1 sorts before string2 |

## Example

> foo.sh

```
#!/bin/bash

# test-string: evaluate the value of a string

ANSWER=maybe

if [ -z "$ANSWER" ]; then
  ehco "There is no answer." >&2
  exit 1
fi

if [ "$ANSWER" = "yes" ]; then
  echo "The answer is YES."
elif [ "$ANSWER" = "no" ]; then
  echo "The answer is No."
elif [ "$ANSWER" = "maybe" ]; then
  echo "The answer is MAYBE."
else
  echo "The answer is UNKNOWN."
fi
```

> Test

```
$ bash foo.sh
The answer is MAYBE.
```
