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
from django.shortcuts import render_to_response
def home_page(request):
    return render_to_response('home.html')
```

> templates/base.html

```
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
    <h1>{% block header_text %}{% endblock %}</h1>
</body>
</html>
```

> templates/home.html

```
{% extends 'base.html' %}
{% block header_text %}Home page{% endblock %}
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`
