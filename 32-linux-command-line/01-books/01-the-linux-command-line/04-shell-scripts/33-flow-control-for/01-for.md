# Flow Control: Looping with for

```
for variable [in words[; do
  commands
done
```

## `for` command on the command line

```
$ for i in A B C D; do echo $i; done
A
B
C
D
```

> brace expansion

```
$ for i in {A..D}; do echo $i; done
A
B
C
D
```

> pathname expansion

```
$ for i in foo*.txt; do echo $i; done
foo1.txt
foo2.txt
foo3.txt
```

## command substitution - find the longest string word

Use the `strings` program to generate a list of readable text "words" in each file

> Setup

```
$ cat foo.txt
Tom
Dick
Harry
```

> foo.sh

```
#!/bin/bash

# longest-word: find longest string in a file
while [[ -n $1 ]]; do
  if [[ -r $1 ]]; then
    max_word=
    max_len=0
    for i in $(strings $1); do  # use the `strings` program to generate a list of readable text "words" in each file
      len=$(echo $i | wc -c);
      if(( len > max_len )); then
        max_len=$len
        max_word=$i
      fi
    done
    echo $max_word
  fi
  shift
done
```

> Test

```
$ bash foo.sh foo.txt
Harry
```
