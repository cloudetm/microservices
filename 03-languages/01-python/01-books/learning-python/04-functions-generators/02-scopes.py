# Python Scope Basics
# Global scope
X = 99  # X and func assigned in module: global
def func(Y):  # Y and Z assigned in function: locals
    # Local scope
    Z = X + Y  # X is a global
    return Z
print(func(1))  # func in module: result=100

X = 88  # Global X
def func():
    X = 99  # Local X: hides global, but we want this here
    print(X)  # 99
func()
print(X)  # Prints 88: unchanged

# The global Statement - tells python that a function plans to change one or more global variables
X = 88  # Global X
def func():
    global X
    X = 99  # Global X: outside def
func()
print(X)  # Prints 99

# Nested Scope Examples
print('Nested Scope')
X = 99  # Global scope name: not used
def f1(name):
    print(name)
    X = 88  # Enclosing def local
    def f2(name):
        print(name)
        print(name + ' is invoked')
        print(X)
        if X:
            print('X is not empty')
            print(X)  # Reference made is nested def
        else:
            print('X is empty')
    f2('f2')
f1('f1')

# function factory
def maker(N):
    def action(X):     # Make and return action
        return X ** N  # action retains N from enclosing scope
    return action
f = maker(2)  # Pass 2 to argument N
print(f(3))  # Pass 3 to X, N remembers 2: 3 ** 2 - 9
print(f(4))  # 4 ** 2 - 16

def maker(N):
    return lambda X: X ** N  # lambda functions retain state too
h = maker(2)
print(h(4))  # 4 ** 2 - 16

def f1():
    x = 88
    def f2(x=x):  # Remember enclosing scope X with defaults
        print(x)
    f2()
f1()  # Prints 88

def f1():
    x = 99  # Pass x along instead of nesting
    f2(x)   # Forward reference OK
def f2(x):
    print(x)  # Flat is still often better than nested!
f1()

def func():
    x = 4
    action = (lambda n: x ** n)  # x remembered from enclosing def
    return action
x = func();
print(x(2))   # 4 ** 2 - 16

def func():
    x = 3
    action = (lambda  n, x=x: x ** n)  # Pass x in manually
    return action
x = func();
print(x(2))   # 3 ** 2 - 9

def makeActions():
    acts = []
    for i in range(5):
        acts.append(lambda x: i ** x)
    return acts

acts = makeActions()
print(acts[0](2))  # should be 0 ** 2 - 16
print(acts[1](2))  # should be 1 ** 2 - 16
print(acts[2](2))  # should be 2 ** 2 - 16
print(acts[4](2))  # should be 4 ** 2 - 16

def makeActions():
    acts = []
    for i in range(5):
        acts.append(lambda x, i=i: i ** x)
    return acts
acts = makeActions()
print(acts[0](2))  # 0 ** 2 - 0
print(acts[1](2))  # 1 ** 2 - 1
print(acts[2](2))  # 2 ** 2 - 4
print(acts[4](2))  # 3 ** 2 - 16
