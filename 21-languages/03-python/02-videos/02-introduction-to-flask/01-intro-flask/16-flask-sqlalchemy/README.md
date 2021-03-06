# Using Flask-SQLAlchemy

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227638.html

https://github.com/miguelgrinberg/oreilly-intro-to-flask-video/tree/master/5a

> pip installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
$ pip install flask-sqlalchemy
```

> requirements

```
# pip freeze > requirements.txt
# cat requirements.txt 
click==6.7
dominate==2.3.1
Flask==0.12.1
Flask-Bootstrap==3.3.7.1
Flask-SQLAlchemy==2.2
Flask-WTF==0.14.2
itsdangerous==0.24
Jinja2==2.9.6
MarkupSafe==1.0
SQLAlchemy==1.1.9
visitor==0.1.3
Werkzeug==0.12.1
WTForms==2.1
```

> app.py

1, from flask_sqlalchemy import SQLAlchemy

2, db = SQLAlchemy(app)

3, db.create_all()

```
from flask import Flask, render_template
from flask_bootstrap import Bootstrap
from wtforms import StringField, SubmitField
from wtforms.validators import DataRequired, Length
from flask_wtf import FlaskForm
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)
app.config['SECRET_KEY'] = 'top secret!'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///data.sqlite3'
bootstrap = Bootstrap(app)
db = SQLAlchemy(app)


class NameForm(FlaskForm):

    name = StringField("What is your name?", validators=[DataRequired(), Length(1, 16)])
    submit = SubmitField('Submit')

class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(16), index=True, unique=True)

    def __repr__(self):
        return '<User {0}>'.format(self.name)

@app.route('/', methods=['GET', 'POST'])
def index():
    name = None
    new = False
    form = NameForm()
    if form.validate_on_submit():
        name = form.name.data
        form.name.data = ''
        if User.query.filter_by(name=name).first() is None:
            db.session.add(User(name=name))
            db.session.commit()
            new=True
    return render_template('index.html', form=form, name=name, new=new)


if __name__ == "__main__":
    db.create_all()
    app.run(debug=True)
```

> templates/index.html
 
```
{% extends "bootstrap/base.html" %}
{% import "bootstrap/wtf.html" as wtf %}

{% block title %}Relational Database Example{% endblock %}

{% block navbar %}
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="{{ url_for('index') }}">Hello</a>
    </div>
</nav>
{% endblock %}

{% block content %}
<div class="container">
    <div class="row">
        <div class="col-md-3">
            {{ wtf.quick_form(form) }}
        </div>
    </div>
    {% if name %}
    <h1>Hello, {{ name }}!</h1>
    {% if new %}
    <p>Pleased to meet you!</p>
    {% else %}
    <p>Happy to see you again!</p>
    {% endif %}
    {% endif %}
</div>
{% endblock %}
```

> Run

```
$ python app.py
```

> Test

```
browser - go to http://127.0.0.1:5000/
enter "Tom" and "Ken"
```

> python query

```
$ python
Python 2.7.10 (default, Feb  6 2017, 23:53:20) 
[GCC 4.2.1 Compatible Apple LLVM 8.0.0 (clang-800.0.34)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>> from app import db, User

>>> User.query.all()
[<User Tom>, <User Ken>]

>>> User.query.filter(User.name.startswith('T')).all()
[<User Tom>]

>>> u = User(name='John')
>>> u
<User John>
>>> db.session.add(u)
>>> db.session.commit()
>>> User.query.all()
[<User Tom>, <User Ken>, <User John>]

>>> db.session.delete(u)
>>> db.session.commit()
>>> User.query.all()
[<User Tom>, <User Ken>]
```

> Table - sqlite

```
$ sqlite3 data.sqlite3 
SQLite version 3.16.0 2016-11-04 19:09:39
Enter ".help" for usage hints.
sqlite> .tables
users
sqlite> select * from users;
1|Tom
2|Ken
```
