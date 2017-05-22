# Simple Arithmetic

> Arithmetic Operators

| Operator | Description |
|----------|-------------|
| +        | Addition    |
| -        | Subtraction |
| *        | Multiplication |
| /        | Integer division |
| **       | Exponentiation |
| %        | Modulo (remainder) |

> `/` - Integer division

```
$ echo $(( 5/2 ))
2
```

> `%` - Modulo operator

```
$ echo $(( 5%2 ))
1
```

## highlight each multiple of 5

> foo.sh

```
#!/bin/bash

# modulo: demonstrate the modulo operator

for ((i = 0; i <= 20; i = i + 1)); do
  remainder=$((i % 5))
  if (( remainder == 0 )); then
    printf "<%d> " $i
  else
    printf "%d " $i
  fi
done
printf "\n"
```

> Test

```
$ bash foo.sh
<0> 1 2 3 4 <5> 6 7 8 9 <10> 11 12 13 14 <15> 16 17 18 19 <20>
```
