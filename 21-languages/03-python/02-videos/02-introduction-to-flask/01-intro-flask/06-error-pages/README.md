# error pages

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227620.html

> error pages

app.py

```
@app.errorhandler(404)
def not_found(e):
    return render_template('404.html')
```

templates/404.html

```
{% extends 'base.html' %}

{% block title %}Not Found{% endblock %}

{% block page_content %}
<h1>Not Found</h1>
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
http://127.0.0.1:5000/foo

Weather
Not Found
```
