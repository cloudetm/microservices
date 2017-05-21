# Accessing the Command Line

The shell provides variables `positional parameters` contain words on the command line.

> foo.sh

greater than nine, surround the number in braces, i.e. ${10}

```
#!/bin/bash

# positional parameters: script to view command line paramters

echo "
\$0 = $0
\$1 = $1
\$2 = $2
\$3 = $3
\$4 = $4
\$5 = $5
\$6 = $6
\$7 = $7
\$8 = $8
\$9 = $9
\$10 = ${10}
"
```

> Test

```
$ bash foo.sh a b c d e f g h i j

$0 = foo.sh
$1 = a
$2 = b
$3 = c
$4 = d
$5 = e
$6 = f
$7 = g
$8 = h
$9 = i
$10 = j
```

## Determining the Number of Arguments

`$#` yields the number of arguments on the command line

> foo.sh

```
#!/bin/bash

# positional parameters: script to view command line paramters

echo "
Number of arguments: $#  # yields the number of arguments
\$0 = $0
\$1 = $1
\$2 = $2
\$3 = $3
\$4 = $4
\$5 = $5
\$6 = $6
\$7 = $7
\$8 = $8
\$9 = $9
\$10 = ${10}
"
```

> Test

```
$ bash foo.sh a b c

Number of arguments: 3  # yields the number of arguments
$0 = foo.sh
$1 = a
$2 = b
$3 = c
$4 = 
$5 = 
$6 = 
$7 = 
$8 = 
$9 = 
$10 = 
```

## shift - Getting Access to Many Arguments

### Large number of arguments

> foo.sh

```
$ cat foo.sh
#!/bin/bash

# positional parameters: script to view command line paramters

echo "
Number of arguments: $#  # yields the number of arguments
\$0 = $0
\$1 = $1
\$2 = $2
\$3 = $3
\$4 = $4
\$5 = $5
\$6 = $6
\$7 = $7
\$8 = $8
\$9 = $9
\$10 = ${10}
"
```

> Test

```
$ bash foo.sh *

Number of arguments: 8  # yields the number of arguments
$0 = foo.sh
$1 = 8
$2 = bar.sh
$3 = distros.txt
$4 = foo1.txt
$5 = foo2.txt
$6 = foo.sh
$7 = phonelist.txt
$8 = temp
$9 = 
$10 = 
```

### shift command

It causes each parameter to "move down one" each time it is executed.

> foo.sh

```
#!/bin/bash

# positional parameters: script to display all arguments

count=1

while [[ $# -gt 0 ]]; do
  echo "Argument $count = $1"
  count=$((count + 1))
  shift  # it causes each parameter to move down one each time it is executed
done
```

> Test

```
$ bash foo.sh a b c
Argument 1 = a
Argument 2 = b
Argument 3 = c
```

## Simple Application - file-information program

> foo.sh

```
#!/bin/bash

# file_info: simple file information program

PROGNAME=$(basename $0)

if [[ -e $1 ]]; then  # `-e` check flow-control/test-expression
  echo -e "\nFile Type:"
  file $1
  echo -e "\nFile Status:"
  stat $1
else
  echo "$PROGNAME: usage: $PROGNAME file" >&2
  exit 1
fi
```

> Test

```
$ bash foo.sh foo1.txt

File Type:
foo1.txt: ASCII text

File Status:
  File: ‘foo1.txt’
  Size: 42        	Blocks: 8          IO Block: 4096   regular file
Device: fd00h/64768d	Inode: 56927       Links: 1
Access: (0664/-rw-rw-r--)  Uid: ( 1000/ vagrant)   Gid: ( 1000/ vagrant)
Context: unconfined_u:object_r:user_home_t:s0
Access: 2017-05-16 16:29:54.684208544 +0000
Modify: 2017-05-11 18:04:00.812718622 +0000
Change: 2017-05-11 18:04:00.814885218 +0000
 Birth: -
```

## Using Positional Parameters with Shell Functions

> call function with parameter

```
file_info () {}

file_info foo1.txt
```

> foo.sh

```
#!/bin/bash

file_info () {
  # file_info: function to display file information

  if [[ -e $1 ]]; then  # `-e` check flow-control/test-expression
    echo -e "\nFile Type:"
    file $1
    echo -e "\nFile Status:"
    stat $1
  else
    echo "$PROGNAME: usage: $PROGNAME file" >&2
    exit 1
  fi
}

file_info foo1.txt
```

> Test

```
$ bash foo.sh

File Type:
foo1.txt: ASCII text

File Status:
  File: ‘foo1.txt’
  Size: 42        	Blocks: 8          IO Block: 4096   regular file
Device: fd00h/64768d	Inode: 56927       Links: 1
Access: (0664/-rw-rw-r--)  Uid: ( 1000/ vagrant)   Gid: ( 1000/ vagrant)
Context: unconfined_u:object_r:user_home_t:s0
Access: 2017-05-16 16:29:54.684208544 +0000
Modify: 2017-05-11 18:04:00.812718622 +0000
Change: 2017-05-11 18:04:00.814885218 +0000
 Birth: -
```
