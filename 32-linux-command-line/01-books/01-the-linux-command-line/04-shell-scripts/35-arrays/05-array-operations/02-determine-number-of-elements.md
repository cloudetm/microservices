# Determining the Number of Array Elements

> number of array elements

```
$ animals=("a dog" "a cat" "a fish")
$ echo ${#animals[@]} # number of array elements
3

$ a[100]=foo
$ echo ${#a[@]} # number of array elements
1
```

> length of element

```
$ echo ${#a[100]} # length of element 100
3
```
