# jinja2 template - access dictionary

> access dictionary

app.py

```
    weather = {
        'January': {'min': 38, 'max': 47, 'rain': 6.14},
        'February': {'min': 38, 'max': 51, 'rain': 4.79},
        'March': {'min': 41, 'max': 56, 'rain': 4.5},
        'April': {'min': 44, 'max': 61, 'rain': 3.4},
        'May': {'min': 49, 'max': 67, 'rain': 2.55},
        'June': {'min': 53, 'max': 73, 'rain': 1.69},
        'July': {'min': 57, 'max': 80, 'rain': 0.59},
        'August': {'min': 58, 'max': 80, 'rain': 0.71},
        'September': {'min': 54, 'max': 75, 'rain': 1.54},
        'October': {'min': 48, 'max': 63, 'rain': 3.42},
        'November': {'min': 41, 'max': 52, 'rain': 6.74},
        'December': {'min': 36, 'max': 45, 'rain': 6.94}
    }
```

templates/index.html - <td>{{ weather[month]['min'] }}</td>

```
        <td>{{ weather[month]['min'] }}</td>
        <td>{{ weather[month]['max'] }}</td>
        <td>{{ weather[month]['rain'] }}</td>
```

> activate virtualenv

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ 
```

> install flask

```
$ pip install flask
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
