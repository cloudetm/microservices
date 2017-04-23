from root import *

class LibraryRunner(Common.Runner):
    def __init__(self):
        Common.Runner.__init__(self)
        self.name = 'LibraryRunner'
    def get_name(self):
        return self.name
