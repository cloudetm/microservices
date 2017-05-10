# Using test

The test command has two equivalent forms:

```
test expression
```

*more popular*

```
[ expression ]
```

## File Expressions

Evaluate the status of files

> test File Expressions

| Expression      | Is true if... |
|-----------------|---------------|
| file1 -ef file2 | file1 and file2 have the same inode numbers (the two filenames refer to the name file by hard linking) |
| file1 -nt file2 | file1 is newer than file2 |
| file1 -ot file2 | file1 is older than file2 |
| -b file         | file exists and is a block-special (device) file |
| -c file         | file exists and is a character-special (device) file |
| -d file         | file exists and is a directory |
| -e file         | file exists |
| -f file         | file exists and is a regular file |
| -g file         | file exists and is set-group-ID |
| -G file         | file exists and is owned by the effective group ID |
| -k file         | file exists and has its "sticky bit" set |
| -L file         | file exists and is a symbolic link |
| -O file         | file exists and is owned by the effective user ID |
| -p file         | file exists and is a named pipe |
| -r file         | file exists and is readable (has readable permission for the effective user) |
| -s file         | file exists and has a length greater than zero |
| -S file         | file exists and is a network socket |
| -t fd           | fd is a file descriptor directed to/from the terminal. This can be used to determine whether standard input/output/error |
| -u file         | file exists and is setuid |
| -w file         | file exists and is writable (has write permission for the effective user) |
| -x file         | file exists and is executable (has execute/search permission for the effective user) |

### Example

> setup - create `foo.txt`

```
touch foo.txt
```

> foo.sh

```
#!/bin/bash

# test-file: Evaluate the status of a file

FILE=foo.txt

if [ -e "$FILE" ]; then
        if [ -f "$FILE" ]; then
                echo "$FILE is a regular file."
        fi
        if [ -d "$FILE" ]; then
                echo "$FILE is a directory."
        fi
        if [ -r "$FILE" ]; then
                echo "$FILE is readable."
        fi
        if [ -w "$FILE" ]; then
                echo "$FILE is writable."
        fi
        if [ -x "$FILE" ]; then
                echo "$FILE is executable/searchable."
        fi
else
        echo "$FILE does not exist"
        exit 1
fi

exit
```

> Test

```
$ bash foo.sh
foo.txt is a regular file.
foo.txt is readable.
foo.txt is writable.
```

## String Expressions

> test String Expressions

| Expression         | Is true if... |
|--------------------|---------------|
| string             | string is not null |
| -n string          | The length of string is greater than zero |
| -z string          | The length of string is zero |
| string1 = string2  | string1 and string2 are equal. Single or double |
| string1 == string2 | preferred equal signs |
| string1 != string2 | string1 and string2 are not equal |
| string1 > string2  | string1 sorts after strings2 |
| string1 < string2  | string1 sorts before string2 |

### Example

> foo.sh

```
#!/bin/bash

# test-string: evaluate the value of a string

ANSWER=maybe

if [ -z "$ANSWER" ]; then
  ehco "There is no answer." >&2
  exit 1
fi

if [ "$ANSWER" = "yes" ]; then
  echo "The answer is YES."
elif [ "$ANSWER" = "no" ]; then
  echo "The answer is No."
elif [ "$ANSWER" = "maybe" ]; then
  echo "The answer is MAYBE."
else
  echo "The answer is UNKNOWN."
fi
```

> Test

```
$ bash foo.sh
The answer is MAYBE.
```

## Integer Expressions

> test Integer Expressions

| Expression            | Is true if... |
|-----------------------|---------------|
| integer1 -eq integer2 | integer1 is equal to integer2 |
| integer1 -ne integer2 | integer1 is not equal to integer2 |
| integer1 -le integer2 | integer1 is less than or equal to integer2 |
| integer1 -lt integer2 | integer1 is less than integer2 |
| integer1 -ge integer2 | integer1 is greater than or equal to integer2 |
| integer1 -gt integer2 | integer1 is greater than integer2 |

### Example

> foo.sh

```
#!/bin/bash

# test-integer: evaluate the value of an integer

INT=-5

if [ -z "$INT" ]; then
  echo "INT is empty." >&2
  exit 1
fi

if [ $INT -eq 0 ]; then
  echo "INT is zero."
else
  if [ $INT -lt 0 ]; then
    echo "INT is negative."
  else
    echo "INT is positive."
  fi
  if [ $((INT % 2)) -eq 0 ]; then
    echo "INT is even."
  else
    echo "INT is odd."
  fi
fi
```

> Test

```
$ bash foo.sh
INT is negative.
INT is odd.
```
