# Syntactic Errors

> foo.sh - script to demonstrate common types of errors

```
      1 #!/bin/bash
      2 
      3 # trouble: script to demonstrate common errors
      4 
      5 number=1
      6 
      7 if [ $number = 1 ]; then
      8   echo "Number is equal to 1.
      9 else
     10   echo "Number is not equal to 1."
     11 fi
```

## Missing Quotes

remove the trailing quote from the first echo argument

> foo.sh

```
#!/bin/bash

# trouble: script to demonstrate common errors

number=1

if [ $number = 1 ]; then
  echo "Number is equal to 1.  # BUG: missing quote
else
  echo "Number is not equal to 1."
fi
```

> Test

```
$ bash foo.sh
foo.sh: line 10: unexpected EOF while looking for matching `"'
foo.sh: line 13: syntax error: unexpected end of file
```

Note: It generates two errors. Interestingly, the line numbers reported are not where the missing quote was removed 
but rather much later in the program.  We can see why if we follow the program after the missing quote. bash will 
continue looking for the closing quote until it finds one, which it does immediately after the second echo command. 
bash becomes very confused after that, and the syntax of the if command is broken because the fi statement is now 
inside a quoted (but open) string.

## Missing or Unexpected Tokens

remove semicolon after the `test` in the if command

> foo.sh

```
#!/bin/bash

# trouble: script to demonstrate common errors

number=1

if [ $number = 1 ] then  # BUG: missing semicolon (;) after test
  echo "Number is equal to 1."
else
  echo "Number is not equal to 1."
fi
```

> Test

```
$ bash foo.sh
foo.sh: line 9: syntax error near unexpected token `else'
foo.sh: line 9: `else'
```

Note: With the semicolon removed, the word then is added to the list of arguments, which syntactically legal. 
The following echo command is legal, too. It's interpreted as another command in the list of commands that 
if will evaluate for an exit code. The else is encountered next, but it's out of space, since 
the shell recognizes it as a reserved word and not the name of a command.

## Unanticipated Expansions

change the value of the number to an empty variable

> foo.sh

```
#!/bin/bash

# trouble: script to demonstrate common errors

number=  # BUG: the number value is missing
if [ $number = 1 ]; then
  echo "Number is equal to 1."
else
  echo "Number is not equal to 1."
fi
```

> Test

```
$ bash foo.sh
foo.sh: line 6: [: =: unary operator expected
Number is not equal to 1.
```

Note: The problem is the expansion of the number variable within the test command. 
Undergoes expansion with number being empty, the result is `[ = 1 ]` which is invalid, and the error is generated. 
The = operator is a binary operator that requires a value on each side, but the first value is missing, 
so the test command expects a unary operator (such as -z) instead.
