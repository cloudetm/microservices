# python
python

## Installation

> https://www.python.org/

> `$ brew install python`

> `$ brew install python3`

### pip installation

> `$ sudo easy_install pip`

> `$ pip install ipython`

### Pick python version

> Preferences, Project: python, Project Interpreter

#### install django and selenium one time

> `sudo pip3 install django --upgrade` when ImportError: No module named 'django' on manage.py

> `$ sudo pip3 install django==1.8.4`

> `$ sudo pip3 install selenium --upgrade`

## PyCharm

### Short cut

https://www.jetbrains.com/pycharm/docs/PyCharm_ReferenceCard_Mac.pdf

## Commands

### Running Code Interactively

> `$ which python`  <- check python version

> Exit: ctrl-d on mac and ctrl-z on windows

```
$ ipython
$ python
>>> print('hi')
hi
>>> import os
>>> os.getcwd()
'/Users/whan/Dropbox/github/python'
```

## Polymorphism in Python

```
def times(x, y):  # Create and assign function
   return x * y  # Body executed when called
times(2, 4)  # Arguments in parentheses
times('Ni', 3)  # Functions are "typeless"

The simple times function depends kinds of objects that x and y are.
The same function can perform multiplication in one instance and repetition in another.
Python leaves it up to the objects to do something reasonable for the syntax.
* is just a dispatch mechanism that routes control to the objects being processed.

This sort of type-dependent behavior is known as polymorphism.
The meaning of the operation depends on the objects being operated upon.
Because it’s a dynamically typed language, every operation is a polymorphic operation in Python:
printing, indexing, the * operator, and much more.
```
