# Using History

A list of commands is kept in your home directory `.bask_history`.
The history facility is a useful resource for reducing the amount of typing you have to do.

## Searching History

> view contents of the history

```
﻿# history | less
```

> find commands that used to list `/usr/bin`

```
﻿# history | grep /usr/bin
   48  ls -l /usr/bin > ls-output.txt
   57  ls -l /usr/bin >> ls-output.txt 
   74  ls -l /usr/bin | less
   75  ls /bin /usr/bin | sort | less
   76  ls /bin /usr/bin | sort | uniq | less
   77  ls /bin /usr/bin | sort | uniq -d | less
   79  ls /bin /usr/bin | sort | uniq | wc -l
   80  ls /bin /usr/bin | sort | uniq | grep zip
   82  ls /usr/bin > ls-output.txt 
   84  ls -l /usr/bin > ls-output.txt 
   87  ls /usr/bin | tail -n 5
   91  ls /usr/bin | tee ls.txt | grep zip
  114  history | grep /usr/bin
```

> `ctrl+r`

```
﻿(reverse-i-search)`': 
```
