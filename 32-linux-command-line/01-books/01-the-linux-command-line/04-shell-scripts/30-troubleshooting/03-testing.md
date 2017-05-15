# Testing

## Stubs

modify the code to make the test safe

> Setup

```
$ mkdir foo
```

> foo.sh

```
#!/bin/bash

dir_name=foo

if [[ -d $dir_name ]]; then
  if cd $dir_name; then
    echo rm * # TESTING
  else
    echo "cannot cd to '%dir_name'" >&2
  fi
else
  echo "no such directory: '$dir_name'" >&2
  exit 1
fi
exit # TESTING
```

> Test

```
$ bash foo.sh
rm *
```
