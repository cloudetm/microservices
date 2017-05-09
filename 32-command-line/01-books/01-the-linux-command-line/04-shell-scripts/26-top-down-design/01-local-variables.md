# Local Variables

Local Variables allow programmers to use variable with names that already exist in globally or in other shell functions
without worry about name conflicts.

> local-vars.sh

```
#!/bin/bash

# local-vars: script to demonstrate local variable

foo=0   # global variable foo

funct_1() {
  local foo  # variable foo local to funct_1

  foo=1
  echo "funct_1: foo = $foo"
}

funct_2() {
  local foo  # variable foo local to funct_2
  
  foo=2
  echo "funct_2: foo = $foo"
}

echo "global: foo = $foo"
funct_1
echo "global: foo = $foo"
funct_2
echo "global: foo = $foo"
```

> Test

```
$ bash local-vars.sh 
global: foo = 0
funct_1: foo = 1
global: foo = 0
funct_2: foo = 2
global: foo = 0
```
