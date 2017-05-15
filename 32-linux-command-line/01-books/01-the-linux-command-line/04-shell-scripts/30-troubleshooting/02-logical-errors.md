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

> foo.sh

```
#!/bin/bash

dir_name=foo

# [[ -d $dir_name ]] && cd $dir_name && rm *

if [[ -d $dir_name ]]; then
  if cd $dir_name; then
    rm *
  else
    echo "cannot cd to '%dir_name'" >&2
  fi
else
  echo "no such directory: '$dir_name'" >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh
no such directory: 'foo'
```
