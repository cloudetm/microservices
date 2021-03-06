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

> superlists/urls.py

> lists/urls.py

> lists/templates/home.html

> lists/templates/list.html

> lists/models.py

> lists/views.py

> lists/tests.py - UnitTest

> functional_tests/tests.py - Functional Test

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

> `$ python3 manage.py test lists`

### Run Functional Test

> `$ python3 manage.py test functional_tests`

### Run All Tests

> `$ python3 manage.py test`
