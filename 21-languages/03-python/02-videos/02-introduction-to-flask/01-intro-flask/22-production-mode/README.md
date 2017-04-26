# Running in Production Mode

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227633.html

> pip installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
$ pip install flask-sqlalchemy
$ pip install flask-login
$ pip install coverage
```

> production.py

```
import os

ADMINS = ['you@example.com']
DEBUG = False
SECRET_KEY = 'top secret!'
SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(
    os.path.dirname(__file__), '../data.sqlite3')
```

> app/__init__.py

```
    # configure production logging of errors
    if not app.config['DEBUG'] and not app.config['TESTING']:
        import logging
        from logging.handlers import SMTPHandler
        mail_handler = SMTPHandler('127.0.0.1', 'error@example.com',
                                   app.config['ADMINS'], 'Application Error')
        mail_handler.setLevel(logging.ERROR)
        app.logger.addHandler(mail_handler)
```
