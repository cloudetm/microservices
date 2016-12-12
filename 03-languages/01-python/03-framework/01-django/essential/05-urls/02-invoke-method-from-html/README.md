## Important

### invoke views method from superlists/urls.py

> lists/templates/home.html

```
<form method="POST" action="/lists/new">
```

> superlists/urls.py

```
  url(r'^lists/new$', 'lists.views.new_list', name='new_list'),
```

## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py - `option + enter` to open context-menu

```
from django.conf.urls import url
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
    url(r'^lists/new$', 'lists.views.new_list', name='new_list'),
]
```

> lists/models.py

```
from django.db import models
class Item(models.Model):
    text = models.TextField(default='')
```

> lists/views.py

```
from django.shortcuts import render
from lists.models import Item
def home_page(request):
    return render(request, 'home.html')
def new_list(request):
    print('# new_list() is invoked')
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items})
```

> templates/home.html

```
<html lang="en">
<head><title>To-Do lists</title></head>
<body>
<h1>Your To-Do list</h1>
<form method="POST" action="/lists/new">
    <input name="item_text" id="id_new_item" placeholder="Enter a to-do item" />
    {% csrf_token %}
</form>
<table id="id_list_table">
    {% for item in items %}
        <tr><td>{{ forloop.counter }}: {{ item.text }}</td></tr>
    {% endfor %}
</table>
</body>
</html>
```

## Database

### models migration

> `$ python3 manage.py makemigrations`

### database migration

> `$ python3 manage.py migrate`

### Reset database

> `$ rm db.sqlite3`

> `$ python3 manage.py migrate --noinput`

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

> Type `abc` in textbox and press `Enter`

> Output on browser: `abc` in table at http://127.0.0.1:8000/lists/new

