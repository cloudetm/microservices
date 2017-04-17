class FirstClass:  # Define a class object
    def setdata(self, value):  # Define class's methods
        self.data = value  # self is the instance
    def display(self):
        print(self.data)  # self.data: per instance

x = FirstClass()  # Make two instances
y = FirstClass()  # Each is a new namespace

x.setdata("Tom")  # Call methods: self is x
y.setdata(3.14)  # Runs: FirstClass.setdata(y, 3.14)

x.display()  # self.data differs in each instance - Tom
y.display()  # Runs: FirstClass.display(y) - 3.14

x.data = "New value"  # Can get/set attributes
x.display()  # Outside the class too - New value

x.anothername = "spam"  # Can set new attributes here too!
print(x.anothername)  # spam

# inheritance
class SecondClass(FirstClass):  # Inherits setdata
    def display(self):  # Changes display
        print('Current value = "%s"' % self.data)

z = SecondClass()
z.setdata(42)  # Finds setdata in FirstClass
z.display()  # Finds overridden method in SecondClass - Current value = "42"

# class named attributes that Python will call automatically
class ThirdClass(SecondClass):  # Inherit from SecondClass
    def __init__(self, value):  # On "ThirdClass(value)"
        self.data = value
    def __add__(self, other):  # On "self + other"
        return ThirdClass(self.data + other)
    def __str__(self):  # On "print(self)", "str()"
        return '[ThirdClass: %s]' % self.data
    def mul(self, other):  # In-place change: named
        self.data *= other

a = ThirdClass('abc')  # __init__ called
a.display()  # Inherited method called - Current value = "abc"
print(a)  # __str__: returns display string - [ThirdClass: abc]

b = a + 'xyz'  # __add__: makes a new instance
b.display()  # b has all ThirdClass methods - Current value = "abcxyz"
print(b)  # [ThirdClass: abcxyz]

a.mul(3)  # mul: changes instance in place
print(a)  # [ThirdClass: abcabcabc]

# Simplest Python class
class rec: pass  # Empty namespace object
rec.name = 'Tom'  # Just objects with attributes
rec.age = 10
print(rec.name)  # Like a C struct or a record - Tom
x = rec()  # Instances inherit class names
y = rec()
print(x.name, y.name)  # name is stored on the class only - ('Tom', 'Tom')
x.name = 'Sue'  # But assignment changes x only
print(rec.name, x.name, y.name)  # ('Tom', 'Sue', 'Tom')
print(list(rec.__dict__.keys()))  # ['age', '__module__', '__doc__', 'name']
print(list(name for name in rec.__dict__ if not name.startswith('__')))  # ['age', 'name']
print(list(x.__dict__.keys()))  # ['name']
print(list(y.__dict__.keys()))  # []
def uppername(obj):
    return obj.name.upper()  # Still needs a self arguments (obj)
print(uppername(x))  # Call as a simple function - SUE
rec.method = uppername  # Now it's a class's method!
print(x.method())  # SUE
print(y.method())  # TOM
print(rec.method(x))  # SUE

# Records Revisited: Classes vs Dictionaries
rec = ('Bob', 40.5, ['dev', 'mgr'])  # Tuple-based record
print(rec[0])  # Bob

rec = {}
rec['name'] = 'Bob'  # Dictionary-based record
rec['age'] = 40.5  # Or {...}, dict(n=v), etc.
rec['jobs'] = ['dev', 'mgr']
print(rec['name'])  # Bob

class rec: pass
rec.name = 'Bob'  # Class-based record
rec.age = 40.5
rec.jobs = ['dev', 'mgr']
print(rec.name)  # Bob

class rec: pass
pers1 = rec()
pers1.name = 'Bob'
pers1.jobs = ['dev', 'mgr']
pers1.age = 40.5
pers2 = rec()
pers2.name = 'Sue'
pers2.jobs = ['dev', 'cto']
print(pers1.name, pers2.name)  # ('Bob', 'Sue')

class Person:
    def __init__(self, name, jobs, age=None):  # class = data + logic
        self.name = name
        self.jobs = jobs
        self.age = age
    def info(self):
        return (self.name, self.jobs)
rec1 = Person('Bob', ['dev', 'mgr'], 40.5)  # Construction calls
rec2 = Person('Sue', ['dev', 'cto'])
print(rec1.jobs, rec2.info())  # (['dev', 'mgr'], ('Sue', ['dev', 'cto']))
