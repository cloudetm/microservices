# Starting an Interactive Session

## Command line

```
$ python
Python 2.7.10 (default, Feb  6 2017, 23:53:20) 
[GCC 4.2.1 Compatible Apple LLVM 8.0.0 (clang-800.0.34)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>> print('Hello world!')
Hello world!
>>> print(2 ** 3)
8
>>> import os
>>> os.getcwd()
'/Users/whan'
>>> exit()
```

## File - script

> script1.py

```
# A first Python script
import sys           # Load a library module
print(sys.platform)
print(2 ** 3)        # Raise 2 to a power
x = 'Spam!'
print(x * 3)         # String repetition 
```

> Run

```
$ python script1.py 
darwin
8
Spam!Spam!Spam!
```

