# Debugging

## Tracing

### place informative messages to display the location of execution

Place the tracing messages to standard error to separate them from normal output

> Setup

```
$ mkdir foo
$ touch foo/foo.txt
```

> foo.sh

```
#!/bin/bash

dir_name=foo

echo "preparing to delete files" >&2
if [[ -d $dir_name ]]; then
  if cd $dir_name; then
echo "deleting files" >&2
    rm *
  else
    echo "cannot cd to '%dir_name'" >&2
  fi
else
  echo "no such directory: '$dir_name'" >&2
  exit 1
fi
echo "file deletion complete" >&2
```

> Test

```
preparing to delete files
deleting files
file deletion complete
```

