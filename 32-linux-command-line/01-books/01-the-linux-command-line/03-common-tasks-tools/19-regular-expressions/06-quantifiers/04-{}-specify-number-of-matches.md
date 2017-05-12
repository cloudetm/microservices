# {} - Match an Element a Specific Number of Times

The { and } metacharacters are used to express minimum and maximum numbers of required matches.

> Specifying the Number of Matches

| Specifier | Meaning |
|-----------|---------|
| {n}       | Match the preceding element if it occurs exactly n times |
| {n,m}     | Match the preceding element if it occurs at least n times, but no more than m times |
| {n,}      | Match the preceding element if it occurs n or more times |
| {,m}      | Match the preceding element if it occurs no more than m times |

> phone numbers

```
^\(?[0-9]{3}\)?  [0-9]{3}-[0-9]{4}$
```

```
$ echo "(555) 123-4567" | grep -E '^\(?[0-9]{3}\)? [0-9]{3}-[0-9]{4}$'
(555) 123-4567
$ echo "555 123-4567" | grep -E '^\(?[0-9]{3}\)? [0-9]{3}-[0-9]{4}$'
555 123-4567

$ echo "5555 123-4567" | grep -E '^\(?[0-9]{3}\)? [0-9]{3}-[0-9]{4}$'
```
