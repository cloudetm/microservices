def f(a):  # a is assigned to (references) the passed object
    a = 99  # Changes local variable a only
b = 88
f(b)  # a and b both reference same 88 initially
print(b)  # b is not changed - 88

def changer(a, b):  # Arguments assigned references to objects
    a = 2  # Changes local name's value only
    b[0] = 'spam'  # Changes shared object in place
X = 1
L = [1, 2]  # Caller:
changer(X, L)  # Pass immutable and mutable objects
print(X, L)  # (1, ['spam', 2])

X = 1
a = X  # They share the same object
a = 2  # Reset 'a' only, 'X' is still 1
print(X)  # 1
L = [1,2]
b = L  # They share the same object
b[0] = 'spam'  # In-place change: 'L' sees the change too
print(L)  # ['spam', 2]

# Avoiding mutable argument changes
X = 1
L = [1, 2]
changer(X, L[:])  # Pass a copy, so our 'L' does not change
print(X, L)  # (1, [1, 2])

def changer(a, b):
    b = b[:]  # Copy input list so we don't impact caller
    a = 2
    b[0] = 'spam'  # Changes our list copy only
X = 1
L = [1, 2]
changer(X, L)
print(X, L)  # (1, [1, 2])
try:
    changer(X, tuple(L))
except Exception, e:
    print(e)

# Simulating output parameters and multiple results
def multiple(x, y):
    x = 2  # Changes local names only
    y = [3,4]
    return x, y  # Return multiple new values in a tuple

x = 11
L = [11,22]
X, L = multiple(X, L)
print(X, L)  # (2, [3, 4])

# Keyword and default
def f(a, b, c): print(a, b, c)
f(1, 2, 3)  # match by position - (1, 2, 3)
f(c=3, b=2, a=1)  # match by name - (1, 2, 3)
f(1, c = 3, b = 2)  # a gets 1 by position, b and c passed by name - (1, 2, 3)

# defaults
def f(a, b=2, c=3): print(a, b, c)  # a required, b and c optional
# Use defaults
f(1)  # (1, 2, 3)
f(a=1)  # (1, 2, 3)

# Override defaults
f(1,4)  # (1, 4, 3)
f(1,4,5)  # (1, 4, 5)

# Choose defaults
f(1, c=6)  # (1, 2, 6)

# function Headers: collecting arguments
# * feature - collects all the positional arguments into a new tuple and assigns the variable args to that tuple.
def f(*args): print(args)
f()  # ()
f(1)  # (1,)
f(1,2,3,4)  # (1, 2, 3, 4)
# ** feature - it works for keyword arguments; it collects them into a new dictionary.
def f(**args): print(args)
f()  # {}
f(a=1,b=2)  # {'a': 1, 'b': 2}

# function headers can combine normal arguments, the *, and the **
def f(a, *pargs, **kargs): print(a, pargs, kargs)
f(1,2,3,x=1,y=2)  # (1, (2, 3), {'y': 2, 'x': 1})

# Calls: unpacking arguments
# * syntax pass arguments to a function in a tuple and let Python unpack them into individual arguments
def func(a,b,c,d): print(a,b,c,d)
args = (1,2)
args += (3,4)
func(*args)  # Same as func(1,2,3,4) - (1, 2, 3, 4)
# ** syntax in a function call unpacks a dictionary of key/value pairs into separate keyword arguments
args = {'a':1,'b':2,'c':3}
args['d'] = 4
func(**args)  # Same as func(a=1, b=2, c=3, d=4) - (1, 2, 3, 4)
# combine normal, positional
func(*(1,2), **{'d':4,'c':3})  # Same as func(1, 2, d=4, c=3) - (1, 2, 3, 4)

# Applying functions generically
def tracer(func, *pargs, **kargs):  # Accept arbitrary arguments
    print('calling:', func.__name__)
    return func(*pargs, ** kargs)  # Pass along arbitrary arguments
def func(a,b,c,d):
    return a + b + c + d
print(tracer(func, 1, 2, c=3, d=4))  # ('calling:', 'func') \n 10
