# jinja2 template

> for loop iterate python array

```
    {% for month in months %}
    <tr>
        <td>{{ month }}</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    {% endfor %}
```

> activate virtualenv

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ which python
/Users/whan/python/venv/bin/python
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
January			
February			
March			
April			
May			
June			
July			
August			
September			
October			
November			
December			
```