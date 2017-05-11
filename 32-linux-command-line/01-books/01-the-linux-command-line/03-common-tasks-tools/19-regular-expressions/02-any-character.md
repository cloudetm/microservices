# The Any Character

`.` matches any character

## Example

> setup - create two foo*.txt files

```
$ cat > foo1.txt
zip is nice
bzip is cool
unzip is not good
Tom has a dog

$ cat > foo2.txt
gzip is awesome
Harry has a cat
```

> Test

```
$ grep -h '.zip' foo*.txt
bzip is cool
unzip is not good
gzip is awesome
```
note: zip was not found because `.zip` need to match four characters
