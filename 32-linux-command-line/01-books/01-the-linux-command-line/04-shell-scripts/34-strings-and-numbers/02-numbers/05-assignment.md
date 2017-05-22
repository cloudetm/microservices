# Assignment

A single `=` performs assignment

```
$ foo=
$ echo $foo

$ if (( foo = 5 )); then echo "It is true."; fi # foo = 5 is assignment
It is true.
$ echo $foo
5
```

> Assignment Operators

| Notation         | Description |
|------------------|-------------|
| parameter=value  | Simple assignment. Assigns value to parameter |
| parameter+=value | Addition. Equivalent to parameter=parameter+value |
| parameter-=value | Subtraction. Equivalent to parameter=parameter-value |
| parameter*=value | Multiplication. Equivalent to parameter=parameter*value |
| parameter/=value | Integer division. Equivalent to parameter=parameter/value |
| parameter%=value | Modulo. Equivalent to parameter=parameter%value |
| parameter++      | Variable post-increment. Equivalent to parameter=parameter+1 |
| parameter--      | Variable post-decrement. Equivalent to parameter=parameter-1 |
| ++parameter      | Variable pre-increment. Equivalent to parameter=parameter+1 |
| --parameter      | Variable pre-decrement. Equivalent to parameter=parameter-1 |

> `parameter++`

```
$ foo=1
$ echo $((foo++))
1
$ echo $foo
2
```

> `++parameter`

```
$ foo=1
$ echo $((++foo))
2
$ echo $foo
2
```

## `++parameter` in for loop example

> foo.sh

```
#!/bin/bash

# modulo: demonstrate the modulo operator

for ((i = 0; i <= 20; ++i)); do
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
