# + - Match an Element One or More times

`+` match at least one instance of the preceding element

> match only lines consisting of groups of one or more alphabetic characters separated by single space

```
^([[:alpha:]]+ ?)+$
```

> Test

```
$ echo "This that" | grep -E '^([[:alpha:]]+ ?)+$'
This that
$ echo "a b c" | grep -E '^([[:alpha:]]+ ?)+$'
a b c

$ echo "a b 9" | grep -E '^([[:alpha:]]+ ?)+$' # not match because it contains a non-alphabetic character
$ echo "abc  d" | grep -E '^([[:alpha:]]+ ?)+$' # not match because more than one space character
```
