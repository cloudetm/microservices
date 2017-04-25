# flask_wtf

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227641.html

> flask forms

app.py

```
from flask_wtf import FlaskForm
```

index.html - <form method="POST" action="{{ url_for('add_comment') }}">

```
    <form method="POST" action="">
        {{ form.name.label }} {{ form.name(size=16) }}
        {% for error in form.name.errors %}
        {{ error }}
        {% endfor %}
        <br>
        {{ form.submit() }}
        {{ form.hidden_tag() }}
    </form>
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
2, click "Submit" on an empty textbox - error occurs
3, type Tom in textbox, press enter
4, type helllllllllllloooooo in textbox, press enter - error occurs
```
