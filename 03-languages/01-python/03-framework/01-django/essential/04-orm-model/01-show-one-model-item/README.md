## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py - `option + enter` to open context-menu

```
from django.conf.urls import url
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
]
```

> lists/views.py

```
from django.shortcuts import render
from lists.models import Item
def home_page(request):
    item = Item()
    item.text = request.POST.get('item_text', '')
    item.save()
    return render(request, 'home.html', {
        'new_item_text': item.text
    })
```

> templates/home.html

```
<html lang="en">
<head><title>To-Do lists</title></head>
<body>
<h1>Your To-Do list</h1>
<form method="post">
    <input name="item_text" id="id_new_item" placeholder="Enter a to-do item"/>
    {% csrf_token %}
</form>
<table id="id_list_table">
    <tr><td>1: {{ new_item_text }}</td></tr>
</table>
</body>
</html>
```

## ORM and Model - models.py

### lists/models.py - create Item class inherts from the Model class, so it can be saved to django.db

```
from django.db import models
class Item(models.Model):
    pass
```

### First database migration - to enable add and remove tables and columns based on models changes

```
$ python3 manage.py makemigrations
Migrations for 'lists':
  0001_initial.py:
    - Create model Item
```

### Add text field without a default

```
from django.db import models
class Item(models.Model):
    text = models.TextField()
```

### New field means new migration

```
$ python3 manage.py makemigrations
You are trying to add a non-nullable field 'text' to item without a default; we can't do that (the database needs something to populate existing rows).
Please select a fix:
 1) Provide a one-off default now (will be set on all existing rows)
 2) Quit, and let me add a default in models.py
Select an option: 2
```

### Add text field with a default

```
from django.db import models
class Item(models.Model):
    text = models.TextField(default='')
```

### Run migration after changed models.py

```
$ python3 manage.py makemigrations
Migrations for 'lists':
  0002_item_text.py:
    - Add field text to item
```

### Create database with migrate

```
$ python3 manage.py migrate
Operations to perform:
  Synchronize unmigrated apps: staticfiles, messages
  Apply all migrations: lists, sessions, admin, contenttypes, auth
Synchronizing apps without migrations:
  Creating tables...
    Running deferred SQL...
  Installing custom SQL...
Running migrations:
  Rendering model states... DONE
  Applying contenttypes.0001_initial... OK
  Applying auth.0001_initial... OK
  Applying admin.0001_initial... OK
  Applying contenttypes.0002_remove_content_type_name... OK
  Applying auth.0002_alter_permission_name_max_length... OK
  Applying auth.0003_alter_user_email_max_length... OK
  Applying auth.0004_alter_user_username_opts... OK
  Applying auth.0005_alter_user_last_login_null... OK
  Applying auth.0006_require_contenttypes_0002... OK
  Applying lists.0001_initial... OK
  Applying lists.0002_item_text... OK
  Applying sessions.0001_initial... OK
```

### Reset database

> `$ rm db.sqlite3`

> `$ python3 manage.py migrate --noinput`

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

> Type `abc` in textbox and press `Enter`

> Output on browser: `abc` in table

