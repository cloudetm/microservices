## Create Project

> `$ django-admin.py startproject superlists`

## Create app

> `$ cd superlists`

> `$ python3 manage.py startapp lists`

## Tests

> functional_tests.py

## See changes on following files

> lists/templates/home.html

```
<html lang="en">
<head>
    <title>To-Do lists</title>
</head>
<body>
    <h1>Your To-Do list</h1>
    <input id="id_new_item" placeholder="Enter a to-do item" />
    <table id="id_list_table">
        <tr>1: Buy peacock feathers</tr>
    </table>
</body>
</html>
```

> lists/views.py

```
from django.shortcuts import render
def home_page(request):
    return render(request, 'home.html')
```

> superlists/settings.py - add lists to INSTALLED_APPS

```
INSTALLED_APPS = (
    'lists',
)
```

> superlists/urls.py

```
from django.conf.urls import patterns, include, url
from django.contrib import admin
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
    url(r'^admin/', include(admin.site.urls)),
]
```

## Launch Server

> `$ python3 manage.py runserver`

## Run Test

> `$ python3 functional_tests.py`

