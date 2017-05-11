# Control Operators: Another Way to Branch

Control operators 

> `&& (AND)`

command2 is executed if command1 is successful

```
command1 && command2
```

> `|| (OR)`

command2 is executed if command1 is unsuccessful

```
command1 || command2
```

## Example

> `&&`

```
$ mkdir temp && cd temp
```

> `||`

```
$ [ -d temp ] || mkdir temp

$ if [ -d temp ]; then echo "true"; fi
true
```
