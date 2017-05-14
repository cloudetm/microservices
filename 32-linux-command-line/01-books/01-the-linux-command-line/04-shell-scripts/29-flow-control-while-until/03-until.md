# until

`until` - An until loop continues until it receives a 0 exit status (success) that is opposite to while

## display five numbers in sequential order from 1 to 5

### until

> foo.sh

```
#!/bin/bash

# until-count: display a series of numbers

count=1

until [ $count -gt 5 ]; do
  echo $count
  count=$((count + 1))
done
echo "Finished."
```

> Test

```
$ bash foo.sh
1
2
3
4
5
Finished.
```

### while

> foo.sh

```
#!/bin/bash

# while-count: display a series of numbers

count=1

while [ $count -le 5 ]; do
  echo $count
  count=$((count + 1))
done
echo "Finished."
```

> Test

```
$ bash foo.sh 
1
2
3
4
5
Finished.
```
