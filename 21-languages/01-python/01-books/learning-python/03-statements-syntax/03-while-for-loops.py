# while loop
x = 'spam'
while x:      # While x is not empty
    print(x)  # print x
    x = x[1:] # Strip first character off x

a = 0; b = 5
while a < b:  # One way to code counter loops
    print(a)
    a += 1    # Or, a = a + 1

# for loop
for x in ["a", "b", "c"]:
    print(x)

sum = 0
for x in [1,2,3,4]:
    sum = sum + x
print(sum)

S = "abc"
for x in S: print(x)

T = ("a", "b", "c")
for x in T: print(x)

# Tuple assignment in for loops
T = [(1,2),(3,4),(5,6)]
for (a, b) in T:
    print(a, b)

# Use dict keys iterator and index
D = {'a': 1, 'b': 2, 'c': 3}
for key in D:
    print(key, '=>', D[key])

print(list(D.items()))

# Iterate over both keys and values
for (key, value) in D.items():
    print(key, '=>', value)

T = [(1,2),(3,4),(5,6)]
for both in T:
    a, b = both
    print(a, b)

# Nested sequences work too
((a,b),c) = ((1,2),3)
print(a, b, c)  # (1, 2, 3)

# range
print(list(range(5)), list(range(2,5)), list(range(0,10,2)))  # ([0, 1, 2, 3, 4], [2, 3, 4], [0, 2, 4, 6, 8])
print(list(range(-5,5)))  # [-5, -4, -3, -2, -1, 0, 1, 2, 3, 4]
print(list(range(5,-5,-1)))  # [5, 4, 3, 2, 1, 0, -1, -2, -3, -4]

# for vs while iteration
X = 'spam'
for item in X: print(item)  # Use this simple iteration if you can

i = 0
while i < len(X):
    print(X[i])
    i += 1

# Slice
S = 'abcd'
print(S[1:])  # bcd - strip first character off S
print(S[:2])  # ab - returns from the beginning to position 1; [0,1]

# Parallel Traversals: zip and map
L1 = [1,2,3,4]
L2 = [5,6,7,8]
print(list(zip(L1, L2)))  # [(1, 5), (2, 6), (3, 7), (4, 8)]

S1 = 'abc'
S2 = 'xyz123'
print(map(None, S1, S2))  # [('a', 'x'), ('b', 'y'), ('c', 'z'), (None, '1'), (None, '2'), (None, '3')]

print(list(map(ord, 'spam')))  # [115, 112, 97, 109]

keys = ['a','b','c']
vals = [1,2,3]
print(list(zip(keys, vals)))  # [('a', 1), ('b', 2), ('c', 3)]

D = {}
for(k,v) in zip(keys,vals): D[k]=v
print(D)  # {'a': 1, 'c': 3, 'b': 2}

D = dict(zip(keys,vals))
print(D)  # {'a': 1, 'c': 3, 'b': 2}
