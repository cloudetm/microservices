# argparse

> https://docs.python.org/2/howto/argparse.html

```
/01-positional-args

$ python 01-echo.py foo
foo

$ python 02-square.py 4
16
```

```
/02-optional-args

$ python 01-verbosity.py --verbosity
verbosity turned on

$ python 01-verbosity.py -v
verbosity turned on

$ python 01-verbosity.py -h
usage: 01-verbosity.py [-h] [-v]

optional arguments:
  -h, --help       show this help message and exit
  -v, --verbosity  increase output verbosity
```

```
/03-positional-optional-args

$ python 01-square-verbose.py 4
16

$ python 01-square-verbose.py 4 -v
the square of 4 equals 16

$ python 02-square-verbosity.py 4
16

$ python 02-square-verbosity.py 4 -v 1
4^2 == 16

$ python 02-square-verbosity.py 4 -v 2
the square of 4 equals 16

$ python 02-square-verbosity.py 4 -v 3
usage: 02-square-verbosity.py [-h] [-v {0,1,2}] square
02-square-verbosity.py: error: argument -v/--verbosity: invalid choice: 3 (choose from 0, 1, 2)
```

```
/11-real-example

$ python 01-type.py -t library
library
```
