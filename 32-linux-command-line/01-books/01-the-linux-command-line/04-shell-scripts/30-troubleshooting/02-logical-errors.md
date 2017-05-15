# Logical Errors

`logical errors` do not prevent a script from running.

## Setup - create a foo.txt

```
$ touch foo.txt

$ ls
foo.sh  foo.txt
```

## Problem

If `cd $dir_name` fails, `rm *` is still executed that will delete files in the current working directory

> foo.sh

```
#!/bin/bash

dir_name=foo

cd $dir_name
rm *
```

> Test

```
$ bash foo.sh
foo.sh: line 5: cd: foo: No such file or directory
$ ls
```

## Solution - better

- If `cd $dir_name` fails, `rm *` is not carried out.
- This is better, but it still leaves open the possibility that the variable `dir_name` is unset or empty, which would
result in the files in the user's home directory being deleted.

> foo.sh

```
#!/bin/bash

dir_name=foo

cd $dir_name && rm *
```

> Test

```
$ bash foo.sh
foo.sh: line 5: cd: foo: No such file or directory
$ ls
foo.sh  foo.txt
```

## Solution - best

Checking to see that dir_name actually contains the directory name

```

```
