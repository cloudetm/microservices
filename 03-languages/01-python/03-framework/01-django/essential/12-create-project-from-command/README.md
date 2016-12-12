## Create Project

> `$ django-admin.py startproject superlists`

## Create app

> `$ cd superlists`

> `$ python3 manage.py startapp lists`

## See changes on following files

> superlists/urls.py

```
from django.conf.urls import patterns, url
urlpatterns = patterns('',
  url(r'^$', 'lists.views.home_page', name='home')
)
```

> lists/views.py

```
from django.http import HttpResponse
def home_page(request):
    return HttpResponse('<html><title>To-Do lists</title><b>To-Do lists</b></html>')
```

## Launch Server

> `$ python3 manage.py runserver`

## Browser visit

> `http://127.0.0.1:8000/`
