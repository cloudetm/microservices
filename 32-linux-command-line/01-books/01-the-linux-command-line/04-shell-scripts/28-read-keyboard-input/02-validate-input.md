# Validating Input

https://www.safaribooksonline.com/library/view/the-linux-command/9781593273897/ch28s02.html

## Validate - filename, file exist, ()float number or integer)

> foo.sh

```
$ cat foo.sh
#!/bin/bash

# read-validate: validate input

invalid_input () {
  echo "Invalid input '$REPLY'" >& 2
  exit 1
}

read -p "Enter a single item > "

# input is empty (invalid)
[[ -z $REPLY ]] && invalid_input

# input is multiple items (invalid)
(( $(echo $REPLY | wc -w) > 1 )) && invalid_input

# is input a valid filename?
if [[ $REPLY =~ ^[-[:alnum:]\._]+$ ]]; then
  echo "'$REPLY' is a valid filename."
  if [[ -e $REPLY ]]; then
    echo "And file '$REPLY' exists."
  else
    echo "However, file '$REPLY' does not exist."
  fi

  # is input a floating point number?
  if [[ $REPLY =~ ^-?[[:digit:]]*\.[[:digit:]]+$ ]]; then
    echo "'$REPLY' is a floating point number."
  else
    echo "'$REPLY' is not a floating point number."
  fi

  # is input an integer?
  if [[ $REPLY =~ ^-?[[:digit:]]+$ ]]; then
    echo "'$REPLY' is an integer."
  else
    echo "'$REPLY' is not an integer."
  fi
else
  echo "The string '$REPLY' is not a valid filename."
fi
```

*regex - `^[-[:alnum:]\._]+$`*

> Test

*integer - `8`*

```
$ touch 8

$ bash foo.sh
Enter a single item > 8
'8' is a valid filename.
And file '8' exists.
'8' is not a floating point number.
'8' is an integer.
```

*mix - `-a_1.b`*

```
$ bash foo.sh
Enter a single item > -a_1.b
'-a_1.b' is a valid filename.
However, file '-a_1.b' does not exist.
'-a_1.b' is not a floating point number.
'-a_1.b' is not an integer.
```
