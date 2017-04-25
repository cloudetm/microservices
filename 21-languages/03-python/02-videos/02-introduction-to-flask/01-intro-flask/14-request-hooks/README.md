# request hooks

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227631.html

> request hooks

*app.py*

1, from flask import g

2, g.when = datetime.now().strftime('%H:%M:%S')

```
from datetime import datetime
from flask import Flask, render_template, session, g
app = Flask(__name__)
app.config['SECRET_KEY'] = 'top secret!'

@app.before_request
def before_request():
    if not 'count' in session:
        session['count'] = 1
    else:
        session['count'] += 1
    g.when = datetime.now().strftime('%H:%M:%S')


@app.route('/')
def index():
    return render_template('index.html', count=session['count'], when=g.when)


@app.route('/other')
def other():
    return render_template('other.html', count=session['count'], when=g.when)


if __name__ == '__main__':
    app.run(debug=True)
```

> activate virtualenv

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ 
```

> Installation

```
$ pip install flask
$ pip install flask-bootstrap
$ pip install flask-wtf
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
1, http://127.0.0.1:5000
2, click other to go to other.html
3, click index to go to index.html
```
