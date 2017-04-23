import module2
'''
starting to load...
done loading.
'''
print(module2.sys)  # <module 'sys' (built-in)>
print(module2.name)  # 42
print(module2.func)  # <function func at 0x10bc63668>
print(module2.klass)  # module2.klass
print(list(module2.__dict__.keys()))  #['name', '__builtins__', '__file__', '__package__', 'sys', 'klass', 'func', '__name__', '__doc__']
print(list(name for name in module2.__dict__.keys() if not name.startswith('__')))  # ['name', 'sys', 'klass', 'func']
print(list(name for name in module2.__dict__ if not name.startswith('__')))  # ['name', 'sys', 'klass', 'func']
print(module2.name, module2.__dict__['name'])  # (42, 42)
