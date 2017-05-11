# grep - Search Through Text

- `grep` means global regular expression print
- `grep` searches text files for the occurrence of a specified regular expression and output matching lines

```
grep [options] regex [file...]
```

> grep Options

| Option | Description |
|--------|-------------|
| -i     | Ignore case |
| -v     | Invert match. Print lines do not contain a match |
| -c     | Print number of matches |
| -l     | Print name of each file that contains a match instead of the lines themselves |
| -L     | Print only the names of files that do not contain matches |
| -n     | Prefix each matching line with the number of the line within the file |
| -h     | For multifile searches, suppress the output of filenames |

## Examples

`enter` and `ctrl-D` to end

```
$ cat > foo1.txt
Tom is 10
Tom is a student

$ cat > foo2.txt
Tom has a dog

$ cat > foo3.txt
Harry is 20
```

> search all of the listed files for the string `Tom` and find three matches

```
$ grep Tom foo*.txt
foo1.txt:Tom is 10
foo1.txt:Tom is a student
foo2.txt:Tom has a dog
```

> `-l` option searches only the files that contained matches rather than the matches themselves

```
$ grep -l Tom foo*.txt
foo1.txt
foo2.txt
```

> `-L` option searches only the files that did not contain a match

```
$ grep -L Tom foo*.txt
foo3.txt
```