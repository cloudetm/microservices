# * - Match an Element Zero or More Times

`*` match item may occur any number of times

> a string starts with an upppercase letter, contains any number of upper, lowercase letters and spaces, and ends with a period

```
[[:upper:]][[:upper:][:lower:] ]*\.
```

- first element `[[:upper]]` - contains uppercase letter
- second element `[[:upper:][:lower:] ]` - contains uppercase, lowercase or a space
- third element `\.` - a period escaped with a backslash
- The second element is trailed with an * metacharacter so that any number of upper- and lowercase letters and spaces may follow it and still match

> Test

```
$ echo "This works." | grep -E '[[:upper:]][[:upper:][:lower:] ]*\.'
This works.
$ echo "This Works." | grep -E '[[:upper:]][[:upper:][:lower:] ]*\.'
This Works.
$ echo "this does not" | grep -E '[[:upper:]][[:upper:][:lower:] ]*\.'
```
