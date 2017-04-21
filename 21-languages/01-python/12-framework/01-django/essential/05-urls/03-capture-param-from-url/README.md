## capture parameter from url

> superlists/urls.py - expression (.+) match any characters, up

```
    url(r'^lists/(.+)/$', 'lists.views.view_list', name='view_list'),
```

> lists/views.py

```
def home_page(request):
    return redirect('/lists/18')
def view_list(request, list_id):
    return HttpResponse('list_id=' + list_id)
```

## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py - `option + enter` to open context-menu

```
from django.conf.urls import url
urlpatterns = [
    url('^$', 'lists.views.home_page', name='home'),
    url(r'^lists/(.+)/$', 'lists.views.view_list', name='view_list')
]
```

> lists/views.py

```
from django.shortcuts import redirect
from django.http import HttpResponse
def home_page(request):
    return redirect('/lists/18')
def view_list(request, list_id):
    return HttpResponse('list_id=' + list_id)
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

> output: `list_id=18`