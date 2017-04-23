# Introduction to Templates

> activate virtualenv

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ which python
/Users/whan/python/venv/bin/python
```

> install flask

```
$ pip install flask
```

> app.py

```
from flask import Flask, render_template # Flask is class, render_template is function
app = Flask(__name__) # app is a new instance of Flask

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/user/<name>') # pass name as arg
def user(name):
    return render_template('user.html', name=name)

if __name__ == '__main__':
    app.run(debug=True)
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
