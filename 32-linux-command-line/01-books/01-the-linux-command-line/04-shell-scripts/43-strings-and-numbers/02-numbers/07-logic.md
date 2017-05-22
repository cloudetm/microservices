# Logic

> Comparison Operators

| Operator          | Description |
|-------------------|-------------|
| <=                | Less than or equal to |
| >=                | Greater than or equal to |
| <                 | Less than |
| >                 | Greater than |
| ==                | Equal to |
| !=                | Not equal to |
| &&                | Logical AND |
| &#124;&#124;      | Logical OR |
| expr1?expr2:expr3 | Comparison (ternary) operator. If expression expr1 evaluates to be non-zero (true) then expr2, else expr3 |

> `0` is considered `false`

```
$ if ((0)); then echo "true"; else echo "false"; fi
false
```

> `non-zero` is considered `true`

```
$ if ((1)); then echo "true"; else echo "false"; fi
true
```

> ternary operator - expr1?expr2:expr3

```
$ a=0
$ ((a<1?++a:--a))
$ echo $a
1
$ ((a<1?++a:--a))
$ echo $a
0
```

```
$ a=0
$ ((a<1?(a+=1):(a-=1)))
$ echo $a
1
$ ((a<1?(a+=1):(a-=1)))
$ echo $a
0
```

## Example - arithmetic loop
 
> foo.sh

```
#!/bin/bash

# arith-loop: script to demonstrate arithmetic operators

finished=0
a=0
printf "a\ta**2\ta**3\n"
printf "=\t====\t====\n"

until ((finished)); do # until exits when success (non-zero) means true
  b=$((a**2))
  c=$((a**3))
  printf "%d\t%d\t%d\n" $a $b $c
  ((a<10?++a:(finished=1)))
done
```

> Test

```
a	a**2	a**3
=	====	====
0	0	0
1	1	1
2	4	8
3	9	27
4	16	64
5	25	125
6	36	216
7	49	343
8	64	512
9	81	729
10	100	1000
```
