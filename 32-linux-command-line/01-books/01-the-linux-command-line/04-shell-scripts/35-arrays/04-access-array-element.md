# Accessing Array Elements

## Example - hours

> foo.sh

```
#!/bin/bash

# hours : script to count files by modification time

usage () {
        echo "usage: $(basename $0) directory" >&2
}

# Check that argument is a directory
if [[ ! -d $1 ]]; then
        usage
        exit 1
fi

# Initialize array
for i in {0..23}; do hours[i]=0; done

# Collect data
for i in $(stat -c %y "$1"/* | cut -c 12-13); do # stat - file status, cut - extract the two hour
        j=${i/#0} # remove leading zero
        ((++hours[j]))
        ((++count))
done

# Display data
echo -e "Hour\tFiles\tHour\tFiles"
echo -e "----\t-----\t----\t-----"
for i in {0..11}; do
        j=$((i + 12))
        printf "%02d\t%d\t%02d\t%d\n" $i ${hours[i]} $j ${hours[j]}
done
printf "\nTotal files = %d\n" $count
```

> Test

```
$ bash foo.sh .
Hour	Files	Hour	Files
----	-----	----	-----
00	0	12	0
01	0	13	0
02	1	14	0
03	0	15	0
04	1	16	0
05	0	17	1
06	2	18	0
07	0	19	0
08	0	20	1
09	0	21	1
10	0	22	2
11	0	23	1

Total files = 10
```
