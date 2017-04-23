# first.py
X = 99  # This code doesn't know about second.py
def setX(new):  # Accessor make external changes explict
    global X  # And can manage access in a single place
    X = new

def log():
    print(X)
