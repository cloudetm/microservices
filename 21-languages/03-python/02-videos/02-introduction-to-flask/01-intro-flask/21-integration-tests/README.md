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

test_login.py

```
import unittest
from app import create_app, db
from app.models import User

class UserModelTestCase(unittest.TestCase):
    def setUp(self):
        self.app = create_app('testing')
        self.app_ctx = self.app.app_context()
        self.app_ctx.push()
        self.client = self.app.test_client(use_cookies=True)
        db.create_all()
        User.register('john', 'cat')

    def tearDown(self):
        db.drop_all()
        self.app_ctx.pop()

    def test_login(self):
        r = self.client.get('/login')
        self.assertEqual(r.status_code, 200)
        self.assertTrue('<h1>Login</h1>' in r.get_data(as_text=True))
        r = self.client.post('/login',
                             data={'username': 'john', 'password': 'cat'},
                             follow_redirects=True)
        self.assertEqual(r.status_code, 200)
        self.assertTrue('<h1>Home page</h1>' in r.get_data(as_text=True))
        r = self.client.get('/protected')
        self.assertEqual(r.status_code, 200)
        self.assertTrue('<h1>Protected page</h1>' in r.get_data(as_text=True))
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
app/main/routes.py        23      3      4      1    85%
app/models.py             21      1      0      0    95%
--------------------------------------------------------
TOTAL                     74      4      4      1    94%
```
