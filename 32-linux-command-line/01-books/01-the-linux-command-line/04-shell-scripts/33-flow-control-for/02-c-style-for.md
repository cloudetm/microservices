# for: C Language Form

```
for (( expression1; expression2; expression3 )); do
        commands
done
```

> C style `for` command

> foo.sh

```
#!/bin/bash

# simple_counter: demo of the C style of command

for (( i=0; i<3; i=i+1 )); do
  echo $i
done
```

> Test

```
$ bash foo.sh
0
1
2
```
