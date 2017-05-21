# Handling Positional Parameters En Masse

It is useful to manage all the positional parameters as a group.

> `*` and `@` Special Parameters

| Parameter | Description |
|-----------|-------------|
| $*        | Expands into the list of positional parameters starting with 1. When surrounded by double quotes, it expands into a double-quoted string containing all the positional parameters, each separated by the first character of the IFS shell variable (by default a space character) |
| $@        | Expands into the list of positional parameters starting with 1. When surrounded by double quotes, it expands each positional parameter into a separate word surrounded by double quotes |

> foo.sh

```
#!/bin/bash

# positional parameters: script to demonstrate $* and $@

print_params () {
  echo "\$1 = $1"
  echo "\$2 = $2"
  echo "\$3 = $3"
  echo "\$4 = $4"
}

pass_params () {
  echo -e "\n" '$* :';    print_params $*
  echo -e "\n" '"$*" :';  print_params "$*"
  echo -e "\n" '$@ :';    print_params $@
  echo -e "\n" '"$@" :';  print_params "$@"
}

pass_params "word" "words with spaces"
```

> Test

```
$ bash foo.sh

 $* :
$1 = word
$2 = words
$3 = with
$4 = spaces

 "$*" :
$1 = word words with spaces
$2 = 
$3 = 
$4 = 

 $@ :
$1 = word
$2 = words
$3 = with
$4 = spaces

 "$@" :
$1 = word
$2 = words with spaces
$3 = 
$4 = 
```
