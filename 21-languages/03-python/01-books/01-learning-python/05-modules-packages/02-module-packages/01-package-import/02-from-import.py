from dir1.dir2 import mod  # Code path here only
'''
dir1 init
dir2 init
in mod.py
'''
print(mod.z)  # Don't repeat path - 3
from dir1.dir2.mod import z
print(z)  # 3
import dir1.dir2.mod as mod  # Use shorter name
print(mod.z)  # 3
from dir1.dir2.mod import z as modz  # Ditto if names clash
print(modz)  # 3
