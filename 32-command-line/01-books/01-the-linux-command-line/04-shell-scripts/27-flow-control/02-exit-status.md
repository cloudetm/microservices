# Exit Status

Exit Status - commands issue a value to the system when they terminate.

- `0 to 255` is value range
- `0` indicates success 
- any other value indicates failure.

## Example

use `$?` to display the `Exit Status`

> success

```
$ ls -d /usr/bin
/usr/bin
$ echo $?
0

$ true
$ echo $?
0
```

> failure

```
$ ls -d /bin/usr
ls: cannot access /bin/usr: No such file or directory
$ echo $?
2

$ false
$ echo $?
1
```

> if statement

```
$ if true; then echo "It's true."; fi
It's true.
$ if false; then echo "It's true."; fi

$ if false; true; then echo "It's true."; fi
It's true.
$ if true; false; then echo "It's true."; fi
```
