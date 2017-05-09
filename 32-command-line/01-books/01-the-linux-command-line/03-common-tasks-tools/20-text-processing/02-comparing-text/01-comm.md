# comm - Compare Two Sorted Files Line by Line

compares two text files, displaying the lines that are unique to each one and the lines they have in common.

```
$ cat > file1.txt
a
b
c
d
$ cat > file2.txt
b
c
d
e
```

> compare two files

```
$ comm file1.txt file2.txt 
a
		b
		c
		d
	e
```

- the first column contains lines unique to the first file argument
- the second column contains lines unique to the second file argument
- the third column contains lines shared by both files

> output only the lines shared by both files

```
$ comm -12 file1.txt file2.txt 
b
c
d
```

