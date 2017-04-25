# page links

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227627.html

> page links

templates/base.html - url_for('index')

```
{% block navbar %}
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container">
            <a class="navbar-brand" href="{{ url_for('index') }}">Weather</a>
        </div>
    </nav>
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
1, http://127.0.0.1:5000/foo

Weather
Not Found

2, click "Return to home page" link
```
