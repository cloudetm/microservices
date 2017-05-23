# Outputting the Entire Contents of an Array

`@` notation is more useful matches the array's "real" contents.

```
$ animals=("a dog" "a cat" "a fish")

$ for i in ${animals[*]}; do echo $i; done
a
dog
a
cat
a
fish

$ for i in ${animals[@]}; do echo $i; done
a
dog
a
cat
a
fish

$ for i in "${animals[*]}"; do echo $i; done
a dog a cat a fish

$ for i in "${animals[@]}"; do echo $i; done # GOOD
a dog
a cat
a fish
```
