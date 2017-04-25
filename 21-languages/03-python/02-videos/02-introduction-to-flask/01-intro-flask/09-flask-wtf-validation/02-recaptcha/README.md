# flask_wtf - recaptcha

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227641.html

## examples

https://github.com/lepture/flask-wtf/tree/master/examples

> flask forms

app.py

```
from flask_wtf.recaptcha import RecaptchaField
```

index.html - <form method="POST" action="{{ url_for('add_comment') }}">

```
        <form method="POST" action="{{ url_for('add_comment') }}">
            {{ form.csrf_token }}
            <p>
                {{ form.comment.label }}<br>
                {{ form.comment(rows=5, cols=40) }}
            </p>
            <p>
                {% for error in form.recaptcha.errors %}
                    {{ error }}
                {% endfor %}
                {{ form.recaptcha }}
            </p>
            <p>
                <input type="submit" value="Add comment">
            </p>
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
2, type Comment
3, check "I'm not a robot"
4, click "Add comment"
```
