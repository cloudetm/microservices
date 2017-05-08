# uniq - Report or Omit Repeated Lines

When given a sorted file, it removes any duplicate lines and sends the results to standard output.

```
$ cat > foo.txt
a
b
c
a
b
c

$ sort foo.txt | uniq
a
b
c
```
