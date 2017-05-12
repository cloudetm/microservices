# Bracket Expressions and Character Classes

`bracket expressions` - match a single character from a specified set of characters

> Setup - foo1.txt and foo2.txt

```
$ cat foo1.txt
unzip
zip
zipinfo
bzip
zipnote
bzip2
gzip

$ cat foo2.txt
czip
gzip3
```

> Test - grep bzip and gzip matches

```
$ grep -h '[bg]zip' foo*.txt
bzip
bzip2
gzip
gzip3
```

> within bracket expressions

- `[^ab]` - `^` indicates negation
- `[a-b]` - `-` indicates a character range

## Negation

When `^` is the first character, the remaining characters must not be present.

> Test - grep not bzip and gzip matches

```
$ grep -h '[^bg]zip' foo*.txt
unzip
czip
```

## Traditional Character Ranges

### Example - find matches begins with an uppercase letter

> Setup - foo1.txt and foo2.txt

```
$ cat foo1.txt
Tom
abc
Harry
xyz

$ cat foo2.txt
123
Apple
eat
```

> Test

matches starting with an uppercase letter

```
$ grep -h '^[A-Z]' foo*.txt
Tom
Harry
Apple
```

matches starting with letters and numbers

```
$ grep -h '^[A-Za-z0-9]' foo*.txt
Tom
abc
Harry
xyz
123
Apple
eat
```

## POSIX Character Classes

> POSIX Character Classes

| Character Class | Description |
|-----------------|-------------|
| [:alnum:]       | The alphanumeric characters; in ASCII, equivalent to [A-Za-z0-9] |
| [:word:]        | The same as [:alnum:]. with the addition of the underscore character (_) |
| [:alpha:]       | The alphabetic characters; in ASCII equivalent to [A-Za-z] |
| [:blank:]       | Includes the space and tab characters |
| [:cntrl:]       | The ASCII control codes; includes the ASCII characters 0 through 31 and 127 |
| [:digit:]       | The numerals 0 through 9 |
| [:graph:]       | The visible characters; in ASCII, includes characters 33 through 126 |
| [:lower:]       | The lowercase letters |
| [:punct:]       | The punctuation characters; in ASCII, equivalent to [-!"#$%&'()*+,./:;<=>?@[\\\]_`{|}˜] |
| [:print:]       | The printable characters; all the characters in [:graph:] plus the space character |
| [:space:]       | The whitespace characters including space, tab, carriage return, newline, vertical tab, and form feed; in ASCII, equivalent to [ \t\r\n\v\f ] |
| [:upper:]       | The uppercase characters |
| [:xdigit:]      | Characters used to express hexadecimal numbers; in ASCII, equivalent to [0-9A-Fa-f] |

> files names start with uppercase letter

```
$ ls /usr/sbin/[[:upper:]]*
/usr/sbin/NetworkManager
```
