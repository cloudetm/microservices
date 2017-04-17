## Create Project

> `$ django-admin.py startproject superlists`

## Create app

> `$ cd superlists`

> `$ python3 manage.py startapp lists`

## See changes on following files

> superlists/settings.py - add lists to INSTALLED_APPS

```
INSTALLED_APPS = (
    'lists',
)
```

> lists/templates/home.html

> lists/models.py

> lists/views.py

> superlists/urls.py

```
from django.conf.urls import patterns, include, url
from django.contrib import admin
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
    url(r'^admin/', include(admin.site.urls)),
]
```

> lists/tests.py

> functional_tests.py

## Database

### models migration

> `$ python3 manage.py makemigrations`

### database migration

> `$ python3 manage.py migrate`

## Launch Server

> `$ python3 manage.py runserver`

## Browser visit

> `http://127.0.0.1:8000/`

## Run Test

### Reset database

> `$ rm db.sqlite3`

> `$ python3 manage.py migrate --noinput`

### Run UnitTest

> `$ python3 manage.py test`

### Run Functional Test

> `$ python3 functional_tests.py`
