# Sorting an Array

> foo.sh

```
#!/bin/bash

# array-sort: Sort an array

a=(f e d c b a)
echo "Original array: ${a[@]}"
a_sorted=($(for i in "${a[@]}"; do echo $i; done | sort)) # command pipeline sort
echo "Sorted array:   ${a_sorted[@]}"
```

> Test

```
$ bash foo.sh
Original array: f e d c b a
Sorted array:   a b c d e f
```
