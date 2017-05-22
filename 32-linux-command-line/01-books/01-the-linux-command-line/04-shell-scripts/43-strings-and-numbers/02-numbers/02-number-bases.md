# Number Bases

> Number Bases

| Notation    | Description |
|-------------|-------------|
| Number      | By default, numbers without any notation are treated as decimal (base 10) integers |
| 0number     | In arithmetic expressions, numbers with a leading zero are considered octal |
| 0xnumber    | Hexadecimal notation |
| base#number | Number is in base |

```
$ echo $((0xff)) # hexadecimal number ff
255

$ echo $((2#11111111)) # eight-digit binary (base 2) number
255
```
