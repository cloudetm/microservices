# String Operations

## string length

expand into the length of the string contained by parameter

```
${#parameter}
```

> Test

```
$ foo="This string is long."
[vagrant@localhost temp]$ echo "'$foo' is ${#foo} characters long."
'This string is long.' is 20 characters long.
```

## substring

extract a portion of the string contained in parameter

```
${parameter:offset}
${parameter:offset:length}
```

> Test

```
$ foo="This string is long."
$ echo ${foo:5}
string is long.
$ echo ${foo:5:6}
string
```

If the value of offset is negative, it means starts from the end of the string.
Negative values must be preceded by a space to prevent confusion with the ${parameter:-word}

```
$ foo="This string is long."
$ echo ${foo: -5}
long.
$ echo ${foo: -5:2}
lo
```

## Remove leading portion of the string

Remove a leading portion of the string contained in parameter defined by pattern.pattern is a wildcard pattern
 
```
${parameter#pattern}
${parameter##pattern}
```

> Test

```
$ foo=file.txt.zip
$ echo ${foo#*.}
txt.zip
$ echo ${foo##*.}
zip
```

## Remove text from the end of the string

Remove text from the end of the string contained in parameter

```
${parameter%pattern}
${parameter%%pattern}
```

> Test

```
$ foo=file.txt.zip
$ echo ${foo%.*}
file.txt
$ echo ${foo%%.*}
file
```

## Search and replace

Search and replace upon the contents of parameter

```
${parameter/pattern/string}
${parameter//pattern/string}
${parameter/#pattern/string}
${parameter/%pattern/string}
```

### `/` - the normal form

the first occurrence of pattern is replaced

> Test

```
$ foo=JPG.JPG
$ echo ${foo/JPG/jpg}
jpg.JPG
```

### `//` form
 
all occurrences are replaced

> Test

```
$ foo=JPG.JPG
$ echo ${foo//JPG/jpg}
jpg.jpg
```

### `/#` form

match to occur at the beginning of the string

> Test

```
$ foo=JPG.JPG
$ echo ${foo/#JPG/jpg}
jpg.JPG
```

### `/%` form

match to occur at the end of the string

> Test

```
$ foo=JPG.JPG
$ echo ${foo/%JPG/jpg}
JPG.jpg
```

### Example - longest-word

> Setup

```
$ cat foo.txt
Tom
Dick
Harry

$ cat foo1.txt
java
python
javascript
```

> foo.sh

```
#!/bin/bash

# longest-word: find longest string in a file

for i; do
  if [[ -r $i ]]; then
    max_word=
    max_len=
    for j in $(strings $i); do
      len=${#j}
      if (( len > max_len )); then
        max_len=$len
        max_word=$j
      fi
    done
    echo "$i: '$max_word' ($max_len characters)"
  fi
  shift
done
```

> Test

```
$ bash foo.sh foo.txt foo1.txt
foo.txt: 'Harry' (5 characters)
foo1.txt: 'javascript' (10 characters)
```
