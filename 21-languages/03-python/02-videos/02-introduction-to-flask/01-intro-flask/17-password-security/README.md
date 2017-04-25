# Password Security

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227628.html

> pip installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
$ pip install flask-sqlalchemy
```

> login.py

```
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from werkzeug.security import generate_password_hash, check_password_hash


app = Flask(__name__)
app.config['SECRET_KEY'] = 'top secret!'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///data.sqlite3'
db = SQLAlchemy(app)


class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(16), index=True, unique=True)
    password_hash = db.Column(db.String(64))

    def set_password(self, password):
        self.password_hash = generate_password_hash(password)

    def verify_password(self, password):
        return check_password_hash(self.password_hash, password)

    @staticmethod
    def register(username, password):
        user = User(username=username)
        user.set_password(password)
        db.session.add(user)
        db.session.commit()
        return user

    def __repr__(self):
        return '<User {0}>'.format(self.username)


if __name__ == "__main__":
    db.create_all()
    app.run(debug=True)
```

> Run

```
(venv) $ python
Python 3.5.0 (v3.5.0:374f501f4567, Sep 12 2015, 11:00:19) 
[GCC 4.2.1 (Apple Inc. build 5666) (dot 3)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>> from login import db, User
>>> db.create_all()
>>> u = User.register('john', 'cat')
>>> User.query.all()
[<User john>]
>>> u.verify_password('cat')
True
>>> u.verify_password('dog')
False
```
