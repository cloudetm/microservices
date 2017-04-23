# flask

http://flask.pocoo.org/

> activate virtualenv

```
$ virtualenv venv
New python executable in venv/bin/python
Installing setuptools, pip, wheel...done.

$ source venv/bin/activate
(venv): $ 
```

## Launch Flask Web-api

> Install Flask

```
(venv): $ pip install Flask
```

> app.py - Flask is Fun

```
from flask import Flask
app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World!"

if __name__ == "__main__":
    app.run()
```

> Run

```
(venv): $ python app.py
ctrl+c to exit
```

> Test

```
browser: http://localhost:5000/
output: Hello World!
```
