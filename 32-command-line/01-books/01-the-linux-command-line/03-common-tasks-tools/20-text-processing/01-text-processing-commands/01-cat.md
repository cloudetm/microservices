# cat - Concatenate Files and Print on Standard Output

> `-A` - display non-printing characters in the text.

a leading `cat` character and follow the line. `enter` and `ctrl-D` to end.

```
$ cat > foo.txt
	The quick brown fox jumped over the lazy dog.
$ cat -A foo.txt
^IThe quick brown fox jumped over the lazy dog.$
```

- `^I` - `ctrl-I` same as a `tab` character
- `$` - trailing spaces

> modify text options

- `-n` - numbers lines, 
- `-s` - suppresses the output of multiple blank lines.

```
$ cat > foo.txt
The quick brown fox


jumed over the lazy dog.
$ cat -ns foo.txt
     1	The quick brown fox
     2	
     3	jumed over the lazy dog.
```

