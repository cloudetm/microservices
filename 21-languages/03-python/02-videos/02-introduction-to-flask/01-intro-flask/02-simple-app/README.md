# A Simple "Hello World" Application

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227619.html

> activate virtualenv

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ 
```

> install flask

```
$ pip install flask
```

> app.py

```
from flask import Flask # Flask is class
app = Flask(__name__) # app is a new instance of Flask

@app.route('/')
def index():
    return "<h1>Hello, World!</h1>"

@app.route('/user/<name>') # pass name as arg
def user(name):
    return '<h1>Hello, {0}!</h1>'.format(name)

if __name__ == '__main__':
    app.run(debug=True) # start web server
```

> Run

```
$ python app.py 
 * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
 * Restarting with stat
 * Debugger is active!
 * Debugger PIN: 360-762-616
```

> Test

```
http://127.0.0.1:5000/
Hello, World!

http://127.0.0.1:5000/user/Tom
Hello, Tom!
```
