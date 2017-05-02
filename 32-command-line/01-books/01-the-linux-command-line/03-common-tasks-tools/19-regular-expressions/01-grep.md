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

