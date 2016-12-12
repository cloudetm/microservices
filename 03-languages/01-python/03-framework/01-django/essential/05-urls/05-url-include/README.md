## url includes

> URL pattern explicitly capture only numerical digits.

> lists/urls.py

```
from django.conf.urls import url
urlpatterns = [
    url(r'^foo/$', 'lists.views.new_list', name='new_list'),
]
```

> lists/views.py

```
from django.http import HttpResponse
from django.shortcuts import redirect
def home_page(request):
    return redirect('/lists/foo')
def new_list(request):
    return HttpResponse('new_list is invoked')
```

> superlists/urls.py - `include` subdir urls

```
    url(r'^lists/', include('lists.urls')),
```

## Create Project and app

> "New Project", "Django", type project-name in "Location", type app-name in "Application name", click "Create"

## See changes on following files

> superlists/urls.py - `option + enter` to open context-menu

```
from django.conf.urls import include, url
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
    url(r'^lists/', include('lists.urls')),
]
```

> lists/urls.py

```
from django.conf.urls import url
urlpatterns = [
    url(r'^foo/$', 'lists.views.new_list', name='new_list'),
]
```

> lists/views.py

```
from django.http import HttpResponse
from django.shortcuts import redirect
def home_page(request):
    return redirect('/lists/foo')
def new_list(request):
    return HttpResponse('new_list is invoked')
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`
