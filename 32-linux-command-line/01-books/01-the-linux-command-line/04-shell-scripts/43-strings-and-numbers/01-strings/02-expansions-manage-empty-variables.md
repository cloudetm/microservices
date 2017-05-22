# Expansions to Manage Empty Variables

> `-` sign

```
${parameter:-word}
```

- If `parameter` is unset or is empty, this expansion results in the value of `word`
- If `parameter` is not empty, the expansion results in the value of `parameter`

```
$ foo=

$ echo ${foo:-"substitute value if unset"}
substitute value if unset
$ echo $foo

$ foo=bar
$ echo ${foo:-"substitute value if unset"}
bar
$ echo $foo
bar
```

> `=` sign

```
${parameter:=word}
```

- If `parameter` is unset or empty, this expansion results in the value of `word`, and the value of word is assigned to `parameter`
- If `parameter` is not empty, the expansion results in the value of `parameter`

```
$ foo=
$ echo ${foo:="default value if unset"}
default value if unset
$ echo $foo
default value if unset

$ foo=bar
$ echo ${foo:="default value if unset"}
bar
$ echo $foo
bar
```

> `?` - question mark

```
${parameter:?word}
```

- If `parameter` is unset or empty, this expansion causes the script to exit with an error, and the contents of `word` are sent to standard error
- If `parameter` is not empty, the expansion results in the value of parameter

```
$ echo ${foo:?"parameter is empty"}
-bash: foo: parameter is empty
$ echo $? # check exit code, 1 means error
1

$ foo=bar
$ echo ${foo:?"parameter is empty"}
bar
$ echo $? # check exit code
0
```

> `+` - plus sign

```
${parameter:+word}
```

- If `parameter` is unset or empty, the expansion results in nothing
- If `parameter` is not empty, the value of word is substituted for parameter, but the value of `parameter` is not changed

```
$ foo=
$ echo ${foo:+"substitute value if set"}

$ foo=bar
$ echo ${foo:+"substitute value if set"}
substitute value if set

$ echo $foo
bar
```
