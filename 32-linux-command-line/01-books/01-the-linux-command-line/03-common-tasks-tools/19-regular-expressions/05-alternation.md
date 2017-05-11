# Alternation

`alternation` allows a match to occur from among a set of expressions.

> simple without alternation

```
$ echo "AAA" | grep AAA
AAA
$ echo "BBB" | grep AAA
```

> alternation

`-E` extended feature

```
$ echo "AAA" | grep -E 'AAA|BBB'
AAA
$ echo "BBB" | grep -E 'AAA|BBB'
BBB
$ echo "CCC" | grep -E 'AAA|BBB'

$ echo "AAA" | grep -E 'AAA|BBB|CCC'
AAA
```

`AAA|BBB` means match either the string AAA or the string BBB

## Combine alternation with regular-expression

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

> Test - extended grep line starts with `bz` or `gz` or `zip`

```
$ grep -Eh '^(bz|gz|zip)' foo*.txt
zip
zipinfo
bzip
zipnote
bzip2
gzip
gzip3
```
