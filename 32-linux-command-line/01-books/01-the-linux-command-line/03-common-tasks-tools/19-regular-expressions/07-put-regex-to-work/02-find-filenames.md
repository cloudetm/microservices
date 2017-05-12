# Finding Ugly Filenames with find

- `find` supports a test based on a regular expression.
- `find` requires the pathname exactly match the regular expression

> find every pathname that contains any character that is not a member of the following set

```
[-_./0-9a-zA-Z]
```

```
$ mkdir ~/{abc}
$ mkdir ~/a{bc}
$ mkdir ~/a{bc}d

$ find . -regex '.*[^-_./0-9a-zA-Z].*'
./.mozilla/extensions/{ec8030f7-c20a-464f-9b0e-13a3a9e97384}
./{abc}
./a{bc}
./a{bc}d
```

- `.` - any characters
- '*' - match zero or more times
- [^...] -  character is not in the set
