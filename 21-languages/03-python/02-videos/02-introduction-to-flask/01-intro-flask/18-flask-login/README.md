# flask login

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227628.html

> pip installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
$ pip install flask-sqlalchemy
$ pip install flask-login
```

> login.py

```
1, from flask_login import LoginManager, UserMixin, login_user, logout_user, login_required
```

> Run

```
$ python login.py
```

> Test

- click `Protected` without login

- click `Protected` after login
