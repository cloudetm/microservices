# web forms

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227641.html

> web forms

*app.py*

1, from flask import request

2, @app.route('/', methods=['GET', 'POST'])

```
from flask import Flask, render_template, request
from flask_bootstrap import Bootstrap
app = Flask(__name__)
bootstrap = Bootstrap(app)

@app.route('/', methods=['GET', 'POST'])
def index():
    name = None
    if (request.method == 'POST' and 'name' in request.form):
        name = request.form['name']
    return render_template('index.html', name=name)

if __name__ == '__main__':
    app.run(debug=True)
```

index.html - <form method="POST" action="">

```
{% block content %}
    <div class="container">
        <form method="POST" action="">
            What is your name? <input type="text" name="name">
            <input type="submit" name="submit" value="Submit">
        </form>
        {% if name %}
            <h1>Hello, {{ name }}</h1>
        {% endif %}
    </div>
{% endblock %}
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
2, type Tom and press enter
Hello, Tom
```
