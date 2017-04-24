# jinja2 template - error pages

> macros

app.py

```
@app.errorhandler(404)
def not_found(e):
    return render_template('404.html')
```

templates/index.html

```
{% extends 'bootstrap/base.html' %}
{% import 'macros.html' as macros %}

{% block title %}Weather Averages for {{ city }}{% endblock %}

{% block navbar %}
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container">
            <a class="navbar-brand" href="#">Weather</a>
        </div>
    </nav>
{% endblock %}

{% block content %}
    <div class="container">
        <h1>Weather Averages</h1>
        <h2>{{ city }}</h2>
        <table class="table table-hover">
            <tr>
                <th>Month</th>
                <th>Min</th>
                <th>Max</th>
                <th>Rainfall</th>
            </tr>
            {% for month in months %}
                {{ macros.weather_row(month,
                                      weather[month]['min'],
                                      weather[month]['max'],
                                      weather[month]['rain'],
                                      highlight['min'],
                                      highlight['max'],
                                      highlight['rain']) }}
            {% endfor %}
        </table>
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
http://127.0.0.1:5000/foo

Weather
Not Found
```
