# Deleting an Array

use `unset` command to delete

> delete an array `$ unset foo`

```
$ foo=(a b c d e f)
$ echo ${foo[@]}
a b c d e f
$ unset foo
$ echo ${foo[@]}
```

> delete single array element `$ unset 'foo[2]'`

```
$ foo=(a b c d e f)
$ echo ${foo[@]}
a b c d e f
$ unset 'foo[2]'
$ echo ${foo[@]}
a b d e f
```
