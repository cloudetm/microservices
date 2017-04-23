X = 1
import mod2
print(X)  # My global X - 1
print(mod2.X)  # mod2's X - 2
print(mod2.mod3.X)  # Nested mod3's X - 3
