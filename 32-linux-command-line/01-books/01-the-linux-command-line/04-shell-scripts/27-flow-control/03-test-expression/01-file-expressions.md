# File Expressions

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

## Example

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
