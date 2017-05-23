# Finding the Subscripts Used by an Array

```
${!array[*]}
${!array[@]}
```

> Example

```
$ foo=([2]=a [4]=b [6]=c)
$ for i in "${foo[@]}"; do echo $i; done
a
b
c

$ for i in "${!foo[@]}"; do echo $i; done
2
4
6
```
