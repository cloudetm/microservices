nudge = 1  # Basic assignment
wink = 2
A, B = nudge, wink  # Tuple assignment
A, B
[C, D] = [nudge, wink]  # List assignment
C, D
[a, b, c] = (1, 2, 3)  # Assign tuple of values to list of names
a, c
(a, b, c) = "ABC"
a, c

string = 'SPAM'
a, b, c, d = string  # Same number on both sides
a, d

a, b, c = string[0], string[1], string[2:]  # Index and slice
a, b, c

a, b = string[:2]  # Slice and concatenate
c = string[2:]
a, b, c

((a, b), c) = ('SP', 'AM')  # Paired by shape and position
a, b, c

for(a, b, c) in [(1, 2, 3), (4, 5, 6)]:  # Simple tuple assignment
    print(a, b, c)

for((a, b), c) in [((1, 2), 3), ((4, 5), 6)]:  # Nested tuple assignment
    print((a, b), c)

a = b = c = 'spam'  # multiple target assignments
a, b, c

a = b = 0
b = b + 1
a, b

a = b = []
b.append(42)
a, b

a = []
b = []  # a and b do not share the same object
b.append(42)
a, b

x = 1
x = x + 1  # Traditional
x

x += 1  # Augmented
x

S = "spam"
S += "SPAM"  # Implied concatenation
S

L = [1, 2]
L = L + [3]  # Concatenate: slower
L

L.append(4)  # Faster, but in place
L

L.extend([7, 8])  # Faster, but in place
L

L += [9, 10]  # Mapped to L.extend([9, 10])
L
