# Pipelines

Read data from standard input and send to standard output is utilized by a shell feature called pipelines.
pipe operator | (vertical bar)

```
command1 | command2
```

> examine the output of any command that produces standard output

```
﻿# ls -l /usr/bin | less
```

## Filters

specified two directories (/bin and /usr/bin), the output of ls would have consisted of two sorted lists,
one for each directory. By including sort in the pipeline, we changed the data to produce a single sorted list

```
﻿# ls /bin /usr/bin | sort | less
```

> uniq - Report or Omit Repeated Lines

```
﻿# ls /bin /usr/bin | sort | uniq | less
```

*list duplicates instead*

```
﻿# ls /bin /usr/bin | sort | uniq -d | less
```

### wc - Print Line, Word, and Byte Counts

```
$ wc --help

﻿# wc ls-output.txt 
 1  9 56 ls-output.txt
```

> `-l` - print the newline counts

the number of items in sorted unique list

```
﻿# ls /bin /usr/bin | sort | uniq | wc -l
1899
```

> `-c` - print the word counts

```
$ echo Tom Dick Harry | wc -w
3
```

### grep - Print Lines Matching a Pattern

grep is a powerful program used to find text patterns within files

```
grep pattern [file...]
```

> find all the files in the sorted unique list of programs that have the word `zip` in the name

```
﻿# ls /bin /usr/bin | sort | uniq | grep zip
bunzip2
bzip2
bzip2recover
funzip
gpg-zip
gunzip
gzip
mzip
preunzip
prezip
prezip-bin
unzip
unzipsfx
zip
zipcloak
zipdetails
zipgrep
zipinfo
zipnote
zipsplit
```

> head/tail - Print First/Last Part of Files

`head` prints the first 5 lines

```
﻿# head -n 5 ls-output.txt 
total 235876
-rwxr-xr-x 1 root root       51920 Feb 18  2016 [
lrwxrwxrwx 1 root root           8 Apr 14 17:14 2to3 -> 2to3-2.7
-rwxr-xr-x 1 root root          96 Nov 19 01:32 2to3-2.7
-rwxr-xr-x 1 root root          96 Nov 17 11:20 2to3-3.5
```

`tail` prints the last 5 lines

```
# tail -n 5 ls-output.txt 
-rwxr-xr-x 1 root root      162688 Nov 20  2015 zipinfo
-rwxr-xr-x 1 root root       81840 Aug 16  2015 zipnote
-rwxr-xr-x 1 root root       81840 Aug 16  2015 zipsplit
-rwxr-xr-x 1 root root       27064 Apr  7  2016 zjsdecode
-rwxr-xr-x 1 root root       10336 Nov 16  2015 zlib-flate
```

`tail` is used in pipelines

```
﻿# ls /usr/bin | tail -n 5
zipinfo
zipnote
zipsplit
zjsdecode
zlib-flate
```

*Monitor log*

Use `-f` option, tail continues to monitor the file and when new lines are appended

```
﻿# tail -f /var/log/messages
```

> tee - Read from Stdin and Output to Stdout and Files

`tee` program reads standard input and copies it to both standard output (allowing data to continue down the pipeline)
and to one or more files. This is useful for capturing a pipeline's contents at an intermediate stage of processing.

*include tee to capture the entire directory listing to ls.txt before grep*

```
﻿# ls /usr/bin | tee ls.txt | grep zip
funzip
gpg-zip
mzip
preunzip
prezip
prezip-bin
unzip
unzipsfx
zip
zipcloak
zipdetails
zipgrep
zipinfo
zipnote
zipsplit
```
