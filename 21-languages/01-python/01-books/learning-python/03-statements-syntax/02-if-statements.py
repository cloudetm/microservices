# Basic
x = 1
if x == 1:
    print('true')
else:
    print('false')

# Multiway Branching
x = 1
if x == 1:
    print('1')
elif x == 2:
    print('2')
else:
    print('unknown')

# A dictionary-based 'switch'
# Use has_key or get for default
choice = 'b'
print({'a': 1,
       'b': 2,
       'c': 3}[choice])

# Handling switch defaults
branch = {'a': 1, 'b': 2, 'c': 3}
print(branch.get('b',
                 'Bad choice'  # default value
                 ))
print(branch.get('z',
                 'Bad choice'  # default value
                 ))

# Open pairs may span lines
L = [1,
     2,
     3]
print(L)

# Backslashes allow continuations
x = 1
y = 2
if x == 1 and \
   y == 2:
   print('true')
else:
    print('false')

# More than one simple statement
x = 1; y = 2; print(x)

# Less than: return True or False (1 or 0)
print(2 < 3, 3 < 2)  # (True, False)

# Return left operand if true
# Else, return right operand (true or false)
print(2 or 3, 3 or 2)  # (2, 3)
print([] or 3)  # 3
print([] or {})  # {}

# Return left operand if false
# Else, return right operand (true or false)
print(2 and 3, 3 and 2)  # (3, 2)
print([] and {})  # []
print(3 and [])  # []

# For strings, nonempty means true
result = 't' if 'something' else 'f'
print(result)  # t
result = 't' if '' else 'f'
print(result)  # f

