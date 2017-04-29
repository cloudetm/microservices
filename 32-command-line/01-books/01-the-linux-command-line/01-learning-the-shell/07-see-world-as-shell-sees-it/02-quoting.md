# Quoting

The shell provides a mechanism called quoting to selectively suppress unwanted expansions.

> remove extra whitespace

```
$ echo this is a     test
this is a test
```

> use double quotes to keep extra whitespace

```
$ echo "this is a     test"
﻿this is a     test
```

> parameter expansion substituted an empty string for the value of $1 because it was an undefined variable

```
$ echo The total is $100.00
The total is 00.00
```

## Double Quotes

> error occurs when try to ls `two words.txt`

```
﻿# touch "two words.txt"

# ls -l two words.txt
ls: cannot access 'two': No such file or directory
ls: cannot access 'words.txt': No such file or directory
```

> using double quotes

```
﻿# ls -l "two words.txt"
-rw-r--r-- 1 root root 0 Apr 29 14:48 two words.txt
```

> add `_` between words

```
﻿# mv "two words.txt" two_words.txt
```

> Parameter expansion, arithmetic expansion, and command substitution take place within double quotes

```
﻿# echo "$USER $((2+2)) $(cal)"
root 4      April 2017       
Su Mo Tu We Th Fr Sa  
                   1  
 2  3  4  5  6  7  8  
 9 10 11 12 13 14 15  
16 17 18 19 20 21 22  
23 24 25 26 27 28 29  
30                    
```

> newlines are considered delimiters by the word splitting mechanism

```
﻿# echo $(cal)
April 2017 Su Mo Tu We Th Fr Sa 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
```

*use double quotes to keep embedded spaces and newlines*

```
# echo "$(cal)"
     April 2017       
Su Mo Tu We Th Fr Sa  
                   1  
 2  3  4  5  6  7  8  
 9 10 11 12 13 14 15  
16 17 18 19 20 21 22  
23 24 25 26 27 28 29  
30                    
```

## Single Quotes

use single quotes to suppress all expansions

> double quotes and single quotes comparison

```
# echo "$USER $((2+2))"
root 4

# echo '$USER $((2+2))'
$USER $((2+2))
```

## Escaping Characters

backslask `\` to quote only a single character.

```
﻿# echo "$5.00"
.00

# echo "\$5.00"
$5.00
```
