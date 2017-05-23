# Assigning Values to an Array

> Single value assignment

```
name[subscript]=value
```

- `name` is the name of the array
- `subscript` is an integer

> Multiple values assignment

```
name=(value1 value2 ...)
```

- `name` is the name of the array
- `value1 value2 ...` are values assigned sequentially

> days

```
$ days=(Sun Mon Tue Wed Thu Fri Sat)
$ echo ${days}
Sun
```

> days - assign to specific element by index

```
$ days=([0]=Sun [1]=Mon [2]=Tue [3]=Wed [4]=Thu [5]=Fri [6]=Sat)
$ echo ${days}
Sun
```
