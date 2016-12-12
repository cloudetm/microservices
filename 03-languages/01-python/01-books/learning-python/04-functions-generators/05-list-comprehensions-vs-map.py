# Manual results collection
res = []
for x in 'spam':
    res.append(ord(x))
print(res)  # [115, 112, 97, 109]

# Apply function to sequence (or other)
res = list(map(ord, 'spam'))
print(res)  # [115, 112, 97, 109]

# Apply expression to sequence (or other)
res = [ord(x) for x in 'spam']
print(res)  # [115, 112, 97, 109]

print([ x ** 2 for x in range(5)])  # list comprehensions - [0, 1, 4, 9, 16]
print(list(map((lambda x: x ** 2), range(5))))  # map - [0, 1, 4, 9, 16]

# Adding Tests and Nested Loops: filter
print([x for x in range(5) if x % 2 == 0])  # list comprehensions - [0, 2, 4]
print(list(filter((lambda x: x % 2 == 0), range(5))))  # map - [0, 2, 4]

# Nested for loop
res = [ x + y for x in [0,1,2] for y in [10,20,30]]
print(res)  # [10, 20, 30, 11, 21, 31, 12, 22, 32]

# List Comprehensions and Matrixes
M = [[1,2,3],
     [4,5,6],
     [7,8,9]]
print(M[1])  # Row 2 - [4, 5, 6]
print(M[1][2])  # Row 2, item 3 - 6
print([row[1] for row in M])  # Column 2 - [2, 5, 8]
print([M[row][1] for row in (0,1,2)])  # Using offsets - [2, 5, 8]

L = [[1,2,3],[4,5,6]]
for i in range(len(L)):
    for j in range(len(L[i])):  # Update in place
        L[i][j] += 10
print(L)  # [[11, 12, 13], [14, 15, 16]]

# Generator Functions and Expressions
# Generator Functions: yield vs return
print('#Generator functions - yield')
def gensquares(N):
    for i in range(N):
        yield i ** 2  # Resume here later
for i in gensquares(5):  # Resume the function
    print(i)
x = gensquares(4)
print(next(x))  # 0
print(next(x))  # 1
print(next(x))  # 4
print(next(x))  # 9
try:
    print(next(x))
except Exception, e:
    print(e)
    print('#error occurs')

def ups(line):
    for sub in line.split(','):
        yield sub.upper()
print(tuple(ups('aaa,bbb,ccc')))  # ('AAA', 'BBB', 'CCC')
print({i: s for (i, s) in enumerate(ups('aaa,bbb,ccc'))})  # {0: 'AAA', 1: 'BBB', 2: 'CCC'}

print('#send')
def gen():
    for i in range(10):
        X = yield i
        print(X)
G = gen()
print(next(G))
print(next(G))
print(G.send(77))
print(next(G))
'''
0
None
1
77
2
None
3
'''
print('#end')

# Generator Expressions: Iterables Meet Comprehensions
print([x ** 2 for x in range(4)])  # List comprehension: build a list - [0, 1, 4, 9]
print((x ** 2 for x in range(4)))  # <generator object <genexpr> at 0x1035fec30>
print(list(x ** 2 for x in range(4)))  # List comprehension equivalence - [0, 1, 4, 9]

G = (x ** 2 for x in range(4))
print(iter(G) is G)  # true
print(next(G))  # 0
print(next(G))  # 1
print(next(G))  # 4
print(next(G))  # 9

for num in (x ** 2 for x in range(4)):  # Calls next() automatically
    print('%s, %s' % (num, num / 2.0))
'''
0, 0.0
1, 0.5
4, 2.0
9, 4.5
'''

print(''.join(x.upper() for x in 'aaa,bbb,ccc'.split(',')))  # AAABBBCCC
a, b, c = (x + '\n' for x in 'aaa,bbb,ccc'.split(','))
print(a, c)  # ('aaa\n', 'ccc\n')

# Generator expressions vs map
print(list(map(lambda x: x * 2, (1,2,3,4))))  # map - [2, 4, 6, 8]
print(list(x * 2 for x in (1,2,3,4)))  # Simpler as generator - [2, 4, 6, 8]

# Generator Functions vs Generator Expressions
G = (c * 4 for c in 'SPAM')  # Generator express
print(list(G))  # Force generator to produce all results - ['SSSS', 'PPPP', 'AAAA', 'MMMM']
def timesfour(S):  # Generator function
    for c in S:
        yield c * 4
G = timesfour('spam')
print(list(G))  # Iterate automatically - ['ssss', 'pppp', 'aaaa', 'mmmm']

G = (c * 4 for c in 'SPAM')
I = iter(G)
print(next(I))  # SSSS
print(next(I))  # PPPP
G = timesfour('spam')
I = iter(G)
print(next(I))  # ssss
print(next(I))  # pppp

# Generation in Built-in Types, Tools, and Classes
D = {'a':1, 'b':2, 'c':3}
x = iter(D)
print(next(x))  # a
print(next(x))  # c

# Generators and library tools
import os
for (root, subs, files) in os.walk('.'):  # Directory walk generator
    for name in files:  # A python 'find' operation
        print(root, name)

# Example: Generating Scrambled Sequences
L, S = [1,2,3], 'spam'
for i in range(len(S)):  # For repeat counts 0..3
    S = S[1:] + S[:1]  # Move front item to the end
    print(S)
'''
pams
amsp
mspa
spam
'''
for i in range(len(L)):
    L = L[1:] + L[:1]  # Slice so any sequence type works
    print(L)
'''
[2, 3, 1]
[3, 1, 2]
[1, 2, 3]
'''
# Generalize to simple function
def scramble(seq):
    res = []
    for i in range(len(seq)):
        res.append(seq[i:] + seq[:i])
    return res
print(scramble('spam'))  # ['spam', 'pams', 'amsp', 'mspa']

def scramble(seq):
    return [seq[i:] + seq[:i] for i in range(len(seq))]
print(scramble('spam'))  # ['spam', 'pams', 'amsp', 'mspa']
print(scramble((1,2,3)))  # [(1, 2, 3), (2, 3, 1), (3, 1, 2)]

# Generator function - yield
def scramble(seq):
    for i in range(len(seq)):  # Generator function
        yield seq[i:] + seq[:i]  # Yield one item per iteration
print(list(scramble('spam')))  # 'spam', 'pams', 'amsp', 'mspa']
print(list(scramble((1,2,3))))  # [(1, 2, 3), (2, 3, 1), (3, 1, 2)]

# Generator expressions
S = 'spam'
G = (S[i:] + S[:i] for i in range(len(S)))  # Generator expression equivalent
print(list(G))  # ['spam', 'pams', 'amsp', 'mspa']

F = lambda seq: (seq[i:] + seq[:i] for i in range(len(seq)))
print(F(S))  # <generator object <genexpr> at 0x107242f50>
print(list(F(S)))  # ['spam', 'pams', 'amsp', 'mspa']
for x in F((1,2,3)):
    print(x)
'''
(1, 2, 3)
(2, 3, 1)
(3, 1, 2)
'''
