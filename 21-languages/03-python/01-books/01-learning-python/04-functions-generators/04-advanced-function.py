# Recursive Functions
def mysum(L):
    if not L:
        return 0
    else:
        return L[0] + mysum(L[1:])  # Call myself recursively
L = [1,2,3,4]
print(mysum(L))  # 10
print(L[1:])

def mysum2(L):  # Use ternary expression
    return 0 if not L else L[0] + mysum2(L[1:])
print(mysum2([1,2,3,4]))  # 10

def mysum3(L):  # Any type, assume one
    return L[0] if len(L) == 1 else L[0] + mysum3(L[1:])
print(mysum3([1,2,3,4]))  # 10
print(mysum3(('a','b','c','d')))  # abcd

# Loop Statements vs recursion
L = [1,2,3,4]
sum = 0
while L:
    sum += L[0]
    L = L[1:]
print(sum)  # 10

L = [1,2,3,4]
sum = 0
for x in L: sum += x
print(sum)  # 10

import sys
print(sys.getrecursionlimit())  # 1000 calls deep default
help(sys.setrecursionlimit)  # Read more about it

# Function Objects: Attributes and Annotations
# Indirect Function Calls: "First Class" Objects
def echo(message):  # Name echo assigned to function object
    x = 1
    y = 2
    print(message)
echo('Direct call')  # Call object through original name - Direct call
x = echo  # Now x references the function too
x('Indirect call')  # Call object through name by adding() - Indirect call!
def indirect(func, arg):
    func(arg)  # Call the passed-in object by adding()
indirect(echo, 'Argument call!')  # Pass the function to another function - Argument call
schedule = [(echo, 'Spam!'), (echo, 'Ham!')]
for(func, arg) in schedule:
    func(arg)  # Call functions embedded in containers
def make(label):  # Make a function but don't call it
    def echo(message):
        print(label + ':' + message)
    return echo
F = make('Spam')  # Label in enclosing scope is retained
F('Ham!')  # Call the function that make returned
def foo():
    print('foo called')
foo()
print(func.__name__)  # echo
print(dir(func))
print(func.__code__)
print(func.__code__.co_varnames)  # ('message', 'x', 'y')
print(func.__code__.co_argcount)  # 1

# Function Attributes - we can attach arbitrary user defined attributes to func
func.count = 0
print(func.count)  # 0
func.count += 1
print(func.count)  # 1
func.handles = 'Button-Press'
print(func.handles)  # Button-Press

# Anonymous Functions: lambda
f = lambda x, y, z: x + y + z
print(f(2,3,4))  # 9
def func(x, y, z): return x + y + z
print(func(2,3,4))  # 9

# jump tables
L = [lambda x: x ** 2,  # Inline function definition
     lambda x: x ** 3,
     lambda x: x ** 4]  # A list of three callable functions

for f in L:
    print(f(2))  # Prints 4, 8, 16

print(L[0](3))  # 9

# dictionaries
key = 'got'
value = {'already': (lambda: 2+2),
 'got': (lambda: 2*4),
 'one': (lambda: 2**6)}[key]()
print(value)  # 8

# Nested
action = (lambda x: (lambda y: x + y))
act = action(99)
print(act(3))  # 102

# Functional Programming Tools
# Mapping Functions over Iterables: map
counters = [1,2,3,4]

# for loop
updated = []
for x in counters:
    updated.append(x + 10)
print(updated)  # Add 10 to each item - [11, 12, 13, 14]

# map function apply a passed-in function
def inc(x): return x + 10  # Function to be run
print(list(map(inc, counters)))  # Collect results - [11, 12, 13, 14]

# Function expression
print(list(map((lambda x: x+3), counters)))  # [4, 5, 6, 7]

# multiple sequence arguments
print(pow(3, 4))  # 3**4 - 81
print(list(map(pow, [1,2,3],[2,3,4])))  # 1**2, 2**3, 3**4 - [1, 8, 81]
