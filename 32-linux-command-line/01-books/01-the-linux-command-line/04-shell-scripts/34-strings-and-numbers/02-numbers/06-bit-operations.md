# Bit Operations

> Bit Operators

| Operator | Description |
|----------|-------------|
| ~        | Bitwise negation. Negate all the bits in a number |
| <<       | Left bitwise shift. Shift all the bits in a number to the left |
| >>       | Right bitwise shift. Shift all the bits in a number to the right |
| &        | Bitwise AND. Perform an AND operation on all the bits in two numbers |
| &#124;   | Bitwise OR. Perform an OR operation on all the bits in two numbers |
| ^        | Bitwise XOR. Perform an exclusive OR operation on all the bits in two numbers |

> powers of 2

```
$ for ((i=0;i<8;++i)); do echo $((1<<i)); done
1
2
4
8
16
32
64
128
```
