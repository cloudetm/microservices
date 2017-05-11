# Anchors

`^` and `$` are anchors

- `^` regex is found at the beginning of line
- `$' regex is found at the end of line

> foo.txt

```
unzip
zip
zipinfo
zipnote
gzip
```

> Test 

`^` the beginning of line

```
$ grep -h '^zip' foo.txt
zip
zipinfo
zipnote
```

`$` the end of line

```
$ grep -h 'zip$' foo.txt
unzip
zip
gzip
```

`^zip$` exact word match

```
$ grep -h '^zip$' foo.txt
zip
```
