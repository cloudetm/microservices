# jinja2 template - flask-bootstrap

> flask bootstrap

app.py

```
from flask_bootstrap import Bootstrap
bootstrap = Bootstrap(app)
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
http://127.0.0.1:5000/

Weather
Weather Averages
Portland, OR
Month	Min	Max	Rainfall
January	38	47	6.14
February	38	51	4.79
March	41	56	4.5
April	44	61	3.4
May	49	67	2.55
June	53	73	1.69
July	57	80	0.59
August	58	80	0.71
September	54	75	1.54
October	48	63	3.42
November	41	52	6.74
December	36	45	6.94
```
