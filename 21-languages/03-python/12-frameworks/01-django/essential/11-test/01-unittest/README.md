## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

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
    response = HttpResponse('home page, request.method=', request.method)
    return response
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

## Debug

> 1, open `superlists` project in a new window

> 2, right click on the test file, and select `Debug` test
