# Seeing the World as the Shell Sees it

- echo - Display a line of text

## Expansion

> prints out its text arguments on standard output

```
﻿# echo this is a tset
this is a tset
```

> `echo *`

Shell expands `*` into something else (in this instance, the names of the files in the current working directory)
before the echo command is executed. When the `enter` key is pressed, the shell automatically expands any qualifying
characters on the command line before the command is carried out, so the echo command never saw the *, on its
expanded result.

```
# echo *
chmod composetest docker-compose.yml Dockerfile ls-error.txt ls-output.txt ls.txt package.json server.js src temp
```

## Pathname Expansion

```
﻿# ls
chmod        docker-compose.yml  ls-error.txt   ls.txt        server.js  temp
composetest  Dockerfile          ls-output.txt  package.json  src

# echo D*
Dockerfile

# echo *s
server.js

# echo [[:upper:]]*
Dockerfile
```

## Tilde Expansion

When `~` is used at the beginning of a word, it expands into the name of the home directory of the named user

```
﻿$ echo ~
/home/william
```

## Arithmetic Expansion

```
$(( expression))
```

The shell allows arithmetic to be performed by expansion.

```
﻿$ echo $((2 + 2))
4
```

```
﻿$ echo Five divided by two equals $((5/2))
Five divided by two equals 2
```

## Brace Expansion

Create multiple text strings from a pattern containing braces

```
﻿$ echo Front-{A,B,C}-Back
Front-A-Back Front-B-Back Front-C-Back
```

integers

```
$ echo Number_{1..3}
Number_1 Number_2 Number_3
```

letters

```
﻿$ echo {A..C}
A B C
```

nested

```
﻿$ echo a{A{1,2},B{3,4}}b
aA1b aA2b aB3b aB4b
```

## Parameter Expansion

```
﻿$ echo $USER
william

$ printenv | less
```
