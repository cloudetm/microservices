# Change local x
from small import x, y  # Copy two names out
x = 42  # Changes my x only
import small
print(small.x)  # 1

# Change variables in other modules
import small  # Get module name
small.x = 42  # Changes x in other module
from small import x
print(x)  # 42
