for x in [1,2,3,4]: print(x ** 2)

# File iterators
myfile = open('temp.txt', 'w')  # Open for text output: create/empty
myfile.write('hello world\n')  # Write a line of text: string
myfile.write('goodbye text file\n')
myfile.write('hi everyone\n')
myfile.close()  # Flush output buffers to disk

print(open('temp.txt').read())

f = open('temp.txt')  # Read file in this directory
print(f.readline())  # readline loads one line one each call
print(f.readline())
print(f.readline())  # Returns empty string at end-of-file

for line in open('temp.txt'):
    print(line.upper())

for line in open('temp.txt').readlines():
    print(line.upper())

f = open('temp.txt')
lines = f.readlines()
print(lines)

lines = [line.rstrip() for line in lines]
print(lines)

lines = [line.rstrip() for line in open('temp.txt')]
print(lines)

print([line.upper() for line in open('temp.txt')])

print([line.rstrip() for line in open('temp.txt') if line[0] == 'h'])

print(list(open('temp.txt')))
print(tuple(open('temp.txt')))
print('&&'.join(open('temp.txt')))

L = [1,2,3]
for X in L:
    print(X ** 2)

D = {'a':1,'b':2,'c':3}

for key in D.keys():
    print(key, D[key])

I = iter(D)
print(next(I))
print(next(I))
print(next(I))

try:
    print(next(I))
except:
    print('error occurs')

# range
R = range(3)
print(R)  # [0, 1, 2]

I = iter(R)  # Use iteration protocol to produce results
print(next(I))  # 0
print(next(I))  # 1
print(next(I))  # 2
print(list(range(3)))  # [0, 1, 2]

E = enumerate('abc')  # enumerate is an iterable too
print(E)  # <enumerate object at 0x10bda2c30>

I = iter(E)
print(next(I))  # (0, 'a')
print(next(I))  # (1, 'b')
print(next(I))  # (2, 'c')
print(list(enumerate('abc')))  # [(0, 'a'), (1, 'b'), (2, 'c')]

# List Comprehensions
L = [1,2,3,4,5]
for i in range(len(L)):
    L[i] += 10
print(L)  # [11, 12, 13, 14, 15]
res = []
for x in L:
    res.append(x + 10)
print(res)  # [21, 22, 23, 24, 25]

# Nested loops: for
print([x + y for x in 'abc' for y in 'xyz'])  # ['ax', 'ay', 'az', 'bx', 'by', 'bz', 'cx', 'cy', 'cz']

res = []
for x in 'abc':
    for y in 'xyz':
        res.append(x + y)
print(res)  # ['ax', 'ay', 'az', 'bx', 'by', 'bz', 'cx', 'cy', 'cz']

print(sum([1,2,3,4]))  # 10
print(any(['spam', '', 'ni']))  # true
print(all(['spam', '', 'ni']))  # False
print(max([3, 2, 5, 1, 4]))  # 5
print(min([3, 2, 5, 1, 4]))  # 1

def f(a, b, c, d): print(a, b, c, d)

f(1,2,3,4)  # (1, 2, 3, 4)

# range allows multiple iterators
R = range(3)
print(R)
I1 = iter(R)
I2 = iter(R)
print(next(I1))  # 0
print(next(I1))  # 1
print(next(I2))  # 0
print(next(I2))  # 1
print(next(I1))  # 2
print(next(I2))  # 2

# dictionary view iterables
D = dict(a=1, b=2, c=3)
print(D)
K = D.keys()
print(K)
I = iter(K)
print(next(I))  # a
print(next(I))  # b
