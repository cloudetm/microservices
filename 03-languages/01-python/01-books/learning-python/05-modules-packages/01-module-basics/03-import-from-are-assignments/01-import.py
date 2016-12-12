# from import
from small import x, y  # Copy two names out
x = 42  # Changes local x only
y[0] = 42  # Changes shared mutable in place

# import
import small  # Get module name (from doesn't)
print(small.x)  # Small's x is not my x - 1
print(small.y)  # But we share a changed mutable - [42, 2]
