# Redirection

The coolest feature of the command line: I/O redirection. The I/O stands for input/output, and with this facility you
can redirect the input and output of commands to and from files, as well as connect multiple commands to make powerful
command pipelines.

> `cat`

Concatenate files

> `sort`

Sort lines of text

> `uniq`

Report or omit repeated lines

> `wc`

Print newline, word, and byte counts for each file

> `grep`

Print lines matching pattern

> `head`

Output the first part of a file

> `tail`

Output the last part of a file

> `tee`

Read from standard input and write to standard output and files

## Redirecting Standard Output

> Send `ls` output to ls-output.txt instead of the screen

```
# ls -l /usr/bin > ls-output.txt
```

large text file `ls-output.txt`

```
# ﻿ls -l ls-output.txt 
-rw-r--r-- 1 root root 109837 Apr 29 08:48 ls-output.txt

# ﻿less ls-output.txt
```

> Error occurs when `ls` the nonexistent directory `/bin/usr`

```
# ﻿ls -l /bin/usr > ls-output.txt
ls: cannot access '/bin/usr': No such file or directory
```

`ls-output.txt` has zero length

```
﻿# ls -l ls-output.txt 
-rw-r--r-- 1 root root 0 Apr 29 08:52 ls-output.txt
```

> truncate a file, create a new, empty file

```
﻿# > ls-output.txt 

# ls -l ls-output.txt 
-rw-r--r-- 1 root root 0 Apr 29 08:59 ls-output.txt
```

> append


```
﻿# ls -l /usr/bin >> ls-output.txt 
# ls -l /usr/bin >> ls-output.txt 

# ls -l ls-output.txt 
-rw-r--r-- 1 root root 219674 Apr 29 09:14 ls-output.txt
```

> Redirecting Standard Error

`2` is placed immeidately before the redirection operator to perform the redirection of standard error to `ls-error.txt`

```
﻿# ls -l /bin/usr 2> ls-error.txt

﻿# cat ls-error.txt 
ls: cannot access '/bin/usr': No such file or directory
```

> Redirecting Standard Output and Standard Error to One File

1, redirect standard output to `ls-output.txt`
2, redirect file descriptor 2 (standard error) to file descriptor 1 (standard output) using notation 2>&1

```
﻿# ls -l /bin/usr > ls-output.txt 2>&1

# cat ls-output.txt 
ls: cannot access '/bin/usr': No such file or directory
```

*streamlined version*

single notation &> to redirect both standard output and standard error to `ls-output.txt`

```
﻿# ls -l /bin/usr &> ls-output.txt 

# cat ls-output.txt 
ls: cannot access '/bin/usr': No such file or directory
```

> Disposing of Unwanted Output

Sometimes silence is golden, we just want to throw it away without output from a command

```
﻿# ls -l /bin/usr 2> /dev/null
```

## CAT - CONCATENATE FILES

Display files without paging. Cat is often used to display short text files.

```
﻿# cat ls-output.txt 
```

