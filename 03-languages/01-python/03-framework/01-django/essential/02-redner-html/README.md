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
from django.shortcuts import render_to_response
def home_page(request):
    return render_to_response('home.html')
```

> templates/home.html

```
<html lang="en">
<head>
    <title>To-Do lists</title>
</head>
<body>
<b>To-Do lists - render html</b>
</body>
</html>
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

