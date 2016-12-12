## Create Project and App

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py

> templates/base.html

> templates/home.html

> templates/list.html

> lists/urls.py

> lists/views.py

> lists/models.py

> lists/tests.py - UnitTest

> functional_tests/tests.py - Functional Test

## Database

### Models migration

> `$ python3 manage.py makemigrations`

### Database migration

> `$ python3 manage.py migrate`

### Reset database

> `$ rm db.sqlite3`

> `$ python3 manage.py migrate --noinput`

## Launch Server

> Click "Debug" button to launch the app

## Browser Visit

> `http://127.0.0.1:8000/`

## Test

### Add Test 

> lists/tests.py

> functional_tests.py

### Run Test

> Right click the `tests.py` and `functional_tests.py` and click Debug
