import dir1.dir2.mod
'''
dir1 init
dir2 init
in mod.py
'''
print(dir1)  # <module 'dir1' from '.../dir1/__init__.pyc'>
print(dir1.dir2)  # <module 'dir1' from '.../dir1/dir2/__init__.pyc'>
print(dir1.dir2.mod)  # <module 'dir1' from '.../dir1/dir2/mod.pyc'>
print(dir1.x)  # 1
print(dir1.dir2.y)  # 2
print(dir1.dir2.mod.z)  # 3
