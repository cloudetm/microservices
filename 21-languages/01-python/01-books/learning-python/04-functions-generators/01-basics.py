def times(x, y):  # Create and assign function
    return x * y  # Body executed when called
result = times(2, 4)  # Arguments in parentheses
print(result)  # 8
result = times('Ni', 3)  # Functions are "typeless"
print(result)  # NiNiNi

def intersect(seq1, seq2):
    res = []               # Start empty
    for x in seq1:         # Scan seq1
        if x in seq2:      # Common item?
            res.append(x)  # Add to end
    return res
s1 = "SPAM"
s2 = "SCAM"
print(intersect(s1, s2))  # Strings - ['S', 'A', 'M']
print([x for x in s1 if x in s2])  # ['S', 'A', 'M']
print(intersect([1, 2, 3], (1, 4)))  # Polymorphism Revisited
