# virtualenv and flask

## virtualenv

> Install virtualenv

```
$ sudo pip install virtualenv
```

> Create project directory

```
$ mkdir learning-flask
$ cd learning-flask/
```

> activate venv

```
learning-flask $ virtualenv venv
New python executable in venv/bin/python
Installing setuptools, pip, wheel...done.

learning-flask $ source venv/bin/activate
(venv):learning-flask $ 
```

## Launch Flask web-api

> Install Flask - one time

```
(venv):learning-flask $ pip install Flask
```

> hello.py

```
from flask import Flask

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World!'

if __name__ == '__main__':
    app.run()
```

> Run Flask Web-api

```
(venv):learning-flask $ python hello.py
ctrl+c to exit
```

browser: http://localhost:5000/
