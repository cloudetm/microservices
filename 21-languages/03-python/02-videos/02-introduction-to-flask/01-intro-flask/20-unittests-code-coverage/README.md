# Unit Tests & Code Coverage

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227642.html

> pip installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
$ pip install flask-sqlalchemy
$ pip install flask-login
$ pip install coverage
```

> TestLoader - test.py

```
#!/usr/bin/env python
import coverage
COV = coverage.coverage(branch=True, include='app/*')
COV.start()

import unittest
suite = unittest.TestLoader().discover('tests')
unittest.TextTestRunner(verbosity=2).run(suite)

COV.stop()
COV.report()
```

> Testcase

```
import unittest
from app import create_app, db
from app.models import User

class UserModelTestCase(unittest.TestCase):
    def setUp(self):
        self.app = create_app('testing')
        self.app_ctx = self.app.app_context()
        self.app_ctx.push()
        db.create_all()

    def tearDown(self):
        db.drop_all()
        self.app_ctx.pop()

    def test_password(self):
        u = User(username='john')
        u.set_password('cat')
        self.assertTrue(u.verify_password('cat'))
        self.assertFalse(u.verify_password('dog'))

    def test_registration(self):
        User.register('john', 'cat')
        u = User.query.filter_by(username='john').first()
        self.assertIsNotNone(u)
        self.assertTrue(u.verify_password('cat'))
        self.assertFalse(u.verify_password('dog'))
```

> Run Test

```
$ python test.py

----------------------------------------------------------------------
Ran 2 tests in 1.134s

OK
Name                   Stmts   Miss Branch BrPart  Cover
--------------------------------------------------------
app/__init__.py           19      0      0      0   100%
app/main/__init__.py       3      0      0      0   100%
app/main/forms.py          8      0      0      0   100%
app/main/routes.py        23     12      4      0    41%
app/models.py             21      2      0      0    90%
--------------------------------------------------------
TOTAL                     74     14      4      0    77%
```
