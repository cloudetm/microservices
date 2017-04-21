print('# Step 1: Making Instance')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):  # Constructor takes three arguments, add defaults
        self.name = name  # Fill out fields when created
        self.job = job  # self is the new instance object
        self.pay = pay
print(__name__)  # __main__
if __name__ == '__main__':  # When run for testing only
    # self-test code
    bob = Person('Bob Smith')  # Test the class
    sue = Person('Sue Jones', job='dev', pay=10000)  # Runs __init__ automatically
    print(bob.name, bob.pay)  # Fetch attached attributes - ('Bob', 0)
    print(sue.name, sue.pay)  # sue's and bob's attrs diff - ('Sue', 10000)

print('# Step 2: Adding Behavior Methods')
class Person:
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job
        self.pay = pay
    def lastName(self):  # Behavior methods
        return self.name.split()[-1]  # self is implied subject
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))  # Must change here only
if __name__ == '__main__':  # When run for testing only
    # self-test code
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=10000)
    print(bob.name, bob.pay)
    print(sue.name, sue.pay)
    print(bob.lastName(), sue.lastName())  # Use the new methods - ('Smith', 'Jones')
    sue.giveRaise(.10)  # instead of hardcoding
    print(sue.pay)

print('# Step 3: Operator Overloading')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job  #
        self.pay = pay
    def lastName(self):
        return self.name.split()[-1]
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))
    def __repr__(self):  # Added method
        return '[Person: %s, %s]' % (self.name, self.pay)  # String to print
if __name__ == '__main__':  # When run for testing only
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=10000)
    print(bob)  # [Person: Bob Smith, 0]
    print(sue)  # [Person: Sue Jones, 10000]
    print(bob.lastName(), sue.lastName())  # ('Smith', 'Jones')
    sue.giveRaise(.10)
    print(sue)  # [Person: Sue Jones, 11000]

print('# Step 4: Customizing Behavior by Subclassing')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job
        self.pay = pay
    def lastName(self):
        return self.name.split()[-1]
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))
    def __repr__(self):  # Added method
        return '[Person: %s, %s]' % (self.name, self.pay)
class Manager(Person):
    def giveRaise(self, percent, bonus=.10):  # Redefine to customize
        Person.giveRaise(self, percent + bonus)  # Call Person's version
if __name__ == '__main__':  # When run for testing only
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=10000)
    print(bob)
    print(sue)
    print(bob.lastName(), sue.lastName())
    sue.giveRaise(.10)
    print(sue)
    tom = Manager('Tom Jones', 'mgr', 50000)  # Make a Manager: __init__
    tom.giveRaise(.10)  # Runs custom version
    print(tom.lastName())  # Runs inherited method - Jones
    print(tom)  # Runs inherited __repr__ - [Person: Tom Jones, 60000]
    print('## Polymorphism in Action')
    print('--All three--')
    for obj in (bob, sue, tom):  # Process objects generically
        obj.giveRaise(.10)  # Run this object's giveRaise
        print(obj)  # Run the common __repr__
'''
[Person: Bob Smith, 0]
[Person: Sue Jones, 12100]
[Person: Tom Jones, 72000]
'''

print('# Step 5: Customizing Constructors')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job
        self.pay = pay
    def lastName(self):
        return self.name.split()[-1]
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))
    def __repr__(self):
        return '[Person: %s, %s]' % (self.name, self.pay)
class Manager(Person):
    def __init__(self, name, pay):  # Redefine constructor
        Person.__init__(self, name, 'mgr', pay)  # Run original with 'mgr'
    def giveRaise(self, percent, bonus=.10):
        Person.giveRaise(self, percent + bonus)
if __name__ == '__main__':  # When run for testing only
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=10000)
    print(bob)
    print(sue)
    print(bob.lastName(), sue.lastName())
    sue.giveRaise(.10)
    print(sue)
    tom = Manager('Tom Jones', 50000)  # Job name not needed:
    tom.giveRaise(.10)
    print(tom.lastName())  # Jones
    print(tom)  # [Person: Tom Jones, 60000]

print('## Combine Classes')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job
        self.pay = pay
    def lastName(self):
        return self.name.split()[-1]
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))
    def __repr__(self):
        return '[Person: %s, %s]' % (self.name, self.pay)
class Manager(Person):  # BAD approach by using combine classes, subclass is better
    def __init__(self, name, pay):
        self.person = Person(name, 'mgr', pay)  # Embed a Person object
    def giveRaise(self, percent, bonus=.10):
        self.person.giveRaise(percent + bonus)  # Intercept and delegate
    def __getattr__(self, attr):
        return getattr(self.person, attr)  # Delegate all other attrs
    def __repr__(self):
        return str(self.person)  # Must overload again
if __name__ == '__main__':  # When run for testing only
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=10000)
    print(bob)
    print(sue)
    print(bob.lastName(), sue.lastName())
    sue.giveRaise(.10)
    print(sue)
    tom = Manager('Tom Jones', 50000)  # Job name not needed:
    tom.giveRaise(.10)
    print(tom.lastName())  # Jones
    print(tom)  # [Person: Tom Jones, 60000]

print('## Aggregate embedded objects into a composite')
class Person:  # Start a class
    def __init__(self, name, job=None, pay=0):
        self.name = name
        self.job = job
        self.pay = pay
    def lastName(self):
        return self.name.split()[-1]
    def giveRaise(self, percent):
        self.pay = int(self.pay * (1 + percent))
    def __repr__(self):
        return '[Person: %s, %s]' % (self.name, self.pay)
class Manager(Person):
    def __init__(self, name, pay):  # Redefine constructor
        Person.__init__(self, name, 'mgr', pay)  # Run original base-class with 'mgr'
    def giveRaise(self, percent, bonus=.10):
        Person.giveRaise(self, percent + bonus)
class Department:
    def __init__(self, *args):
        self.members = list(args)
    def addMember(self, person):
        self.members.append(person)
    def giveRaises(self, percent):
        for person in self.members:
            person.giveRaise(percent)
    def showAll(self):
        for person in self.members:
            print(person)
if __name__ == '__main__':
    bob = Person('Bob Smith')
    sue = Person('Sue Jones', job='dev', pay=100000)
    tom = Manager('Tom Jones', 50000)
    development = Department(bob, sue)  # Embed objects in a composite
    development.addMember(tom)
    development.giveRaises(.10)  # Runs embedded objects' giveRaise
    development.showAll()  # Runs embedded objects' __repr__
'''
[Person: Bob Smith, 0]
[Person: Sue Jones, 110000]
[Person: Tom Jones, 60000]
'''
