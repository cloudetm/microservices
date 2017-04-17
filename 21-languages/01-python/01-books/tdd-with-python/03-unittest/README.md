## Create Project

> `$ django-admin.py startproject superlists`

## Create app

> `$ cd superlists`

> `$ python3 manage.py startapp lists`

## Tests

> lists/tests.py

```
from django.core.urlresolvers import resolve
from django.test import TestCase
from django.http import HttpRequest
from lists.views import home_page
class HomePageTest(TestCase):
    def test_root_url_resolves_to_home_page_view(self):
        found = resolve('/')
        self.assertEqual(found.func, home_page)
    def test_home_page_returns_correct_html(self):
        request = HttpRequest()
        response = home_page(request)
        self.assertTrue(response.content.startswith(b'<html>'))
        self.assertIn(b'<title>To-Do lists</title>', response.content)
        self.assertTrue(response.content.endswith(b'</html>'))
```

> lists/views.py

```
from django.shortcuts import render
from django.http import HttpResponse
def home_page(request):
    return HttpResponse('<html><title>To-Do lists</title></html>')
```

> superlists/urls.py

```
from django.conf.urls import patterns, include, url
from django.contrib import admin
urlpatterns = [
    url(r'^$', 'lists.views.home_page', name='home'),
    url(r'^admin/', include(admin.site.urls)),
]
```

## Run UnitTest

> `$ python3 manage.py test`


