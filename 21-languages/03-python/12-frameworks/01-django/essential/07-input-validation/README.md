## input validation

> call `.full_clean()` for validation

> lists/views.py

```
from django.conf.urls import url
urlpatterns = [
    url(r'^foo/$', 'lists.views.new_list', name='new_list'),
]
```

> lists/views.py

```
from django.shortcuts import render
from django.core.exceptions import ValidationError
from lists.models import Item
def home_page(request):
    error = None
    if request.method == 'POST':
        item = Item(text=request.POST['item_text'])
        try:
            item.full_clean()
            item.save()
        except ValidationError:
            error = "You can't have an empty list item"
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items, 'error': error})
```

> templates/home.html

```
<html lang="en">
<head><title>To-Do lists</title></head>
<body>
<h1>Your To-Do list</h1>
<form method="POST">
    <input name="item_text" id="id_new_item" placeholder="Enter a to-do item" />
    {% csrf_token %}
    {% if error %}
        <div class="form-group has-error">
            <span class="help-block">{{ error }}</span>
        </div>
    {% endif %}
</form>
<table id="id_list_table">
    {% for item in items %}
        <tr><td>{{ forloop.counter }}: {{ item.text }}</td></tr>
    {% endfor %}
</table>
</body>
</html>
```

## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py - `option + enter` to open context-menu

```
from django.conf.urls import include, url
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
]
```

> lists/views.py

```
from django.shortcuts import render
from django.core.exceptions import ValidationError
from lists.models import Item
def home_page(request):
    error = None
    if request.method == 'POST':
        item = Item(text=request.POST['item_text'])
        try:
            item.full_clean()
            item.save()
        except ValidationError:
            error = "You can't have an empty list item"
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items, 'error': error})
```

> templates/home.html

```
<html lang="en">
<head><title>To-Do lists</title></head>
<body>
<h1>Your To-Do list</h1>
<form method="POST">
    <input name="item_text" id="id_new_item" placeholder="Enter a to-do item" />
    {% csrf_token %}
    {% if error %}
        <div class="form-group has-error">
            <span class="help-block">{{ error }}</span>
        </div>
    {% endif %}
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

> Type nothing in textbox and press `Enter`

> Output on browser: "You can't have an empty list item"
