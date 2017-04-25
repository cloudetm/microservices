# responses

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227632.html

> responses

app.py


```
from flask import Flask, render_template, make_response, jsonify, redirect, \
    url_for
app = Flask(__name__)


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/text')
def text():
    return render_template('text.txt'), 200, {'Content-Type': 'text/plain'}


@app.route('/xml')
def xml():
    return '<h1>this is shown as <b>XML</b> in the browser</h1>', 200, \
           {'Content-Type': 'application/xml'}


@app.route('/json')
def json():
    return jsonify({'first_name': 'John', 'last_name': 'Smith'})


@app.route('/redirect')
def redir():
    return redirect(url_for('text'))


@app.route('/cookie')
def cookie():
    resp = redirect(url_for('index'))
    resp.set_cookie('cookie', 'Hello, I\'m a cookie')
    return resp


@app.route('/error')
def error():
    return 'Bad Request', 400


@app.route('/response')
def response():
    resp = make_response(render_template('text.txt'))
    resp.headers['Content-Type'] = 'text/plain'
    return resp


if __name__ == '__main__':
    app.run(debug=True)
```

index.html

```
<h1>This page contains HTML</h1>
<p>
    Cookie: 
    {% if 'cookie' in request.cookies %}
        <b>{{ request.cookies['cookie'] }}</b>
    {% else %}
        not set
    {% endif %}
</p>
<p><a href="{{ url_for('text') }}">Click here</a> to view a page that contains plain text.</p>
<p><a href="{{ url_for('xml') }}">Click here</a> to view a page that contains XML data.</p>
<p><a href="{{ url_for('json') }}">Click here</a> to view a page that contains JSON data.</p>
<p><a href="{{ url_for('redir') }}">Click here</a> to view a page that redirects to the plain text page.</p>
<p><a href="{{ url_for('cookie') }}">Click here</a> to view a page that sets a cookie and then redirects back to this page.</p>
<p><a href="{{ url_for('response') }}">Click here</a> to view a page that shows plain text through a response object.</p>
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
2, click each link
```
