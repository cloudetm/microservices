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
from django.shortcuts import render
from django.http import HttpResponse
def home_page(request):
    if request.method == 'POST':
        return HttpResponse(request.POST['item_text'])
    return render(request, 'home.html')
```

> templates/home.html

```
<html lang="en">
<head><title>To-Do lists</title></head>
<body>
<h1>Your To-Do list</h1>
<form method="POST">
    <input name="item_text" id="id_new_item" placeholder="Enter a to-do item" />
    {% csrf_token %}
</form>
<table id="id_list_table"/>
</body>
</html>
```

## Launch Server

> click "Debug" button to launch the app

## Browser visit

> `http://127.0.0.1:8000/`

> Type `abc` in textbox and press `Enter`

> Output on browser: `abc`

## Note

### Use template

```
1, To identify POST requests come from the original site, add the CSRF token using {% csrf_token %}
2, lists/views.py - if request.method == 'POST' return HttpResponse() 
```

