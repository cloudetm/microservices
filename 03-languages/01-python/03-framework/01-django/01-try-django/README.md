## Reference

> https://www.youtube.com/playlist?list=PLEsfXFp6DpzRcd-q4vR5qAgOZUuz8041S

> https://www.djangoproject.com/download/

> https://docs.djangoproject.com/en/1.8/ref/models/fields/

## Tuturial

> Start Project, First Migration, Admin & Superuser, Apps, First View & Url Routing, Template Config, Models, Admin

```
LMDV-WHAN:01-try-django whan$ mkdir trydjango18
LMDV-WHAN:01-try-django whan$ cd trydjango18/
LMDV-WHAN:trydjango18 whan$ virtualenv .
New python executable in ./bin/python
Installing setuptools, pip, wheel...done.
LMDV-WHAN:trydjango18 whan$ source bin/activate
(trydjango18)LMDV-WHAN:trydjango18 whan$ pip install Django==1.8.4
Collecting Django==1.8.4
  Using cached Django-1.8.4-py2.py3-none-any.whl
Installing collected packages: Django
Successfully installed Django-1.8.4
(trydjango18)LMDV-WHAN:trydjango18 whan$ pip freeze
Django==1.8.4
wheel==0.24.0
(trydjango18)LMDV-WHAN:trydjango18 whan$ django-admin.py startproject src
(trydjango18)LMDV-WHAN:trydjango18 whan$ cd src
(trydjango18)LMDV-WHAN:src whan$ python manage.py migrate
Operations to perform:
  Synchronize unmigrated apps: staticfiles, messages
  Apply all migrations: admin, contenttypes, auth, sessions
Synchronizing apps without migrations:
  Creating tables...
    Running deferred SQL...
  Installing custom SQL...
Running migrations:
  Rendering model states... DONE
  Applying contenttypes.0001_initial... OK
  Applying auth.0001_initial... OK
  Applying admin.0001_initial... OK
  Applying contenttypes.0002_remove_content_type_name... OK
  Applying auth.0002_alter_permission_name_max_length... OK
  Applying auth.0003_alter_user_email_max_length... OK
  Applying auth.0004_alter_user_username_opts... OK
  Applying auth.0005_alter_user_last_login_null... OK
  Applying auth.0006_require_contenttypes_0002... OK
  Applying sessions.0001_initial... OK
(trydjango18)LMDV-WHAN:src whan$ python manage.py syncdb
/Users/whan/Dropbox/github/python-repo/10-1/python/03-framework/01-django/01-try-django/trydjango18/lib/python2.7/site-packages/django/core/management/commands/syncdb.py:24: RemovedInDjango19Warning: The syncdb command will be removed in Django 1.9
  warnings.warn("The syncdb command will be removed in Django 1.9", RemovedInDjango19Warning)
Operations to perform:
  Synchronize unmigrated apps: staticfiles, messages
  Apply all migrations: admin, contenttypes, auth, sessions
Synchronizing apps without migrations:
  Creating tables...
    Running deferred SQL...
  Installing custom SQL...
Running migrations:
  No migrations to apply.
You have installed Django's auth system, and don't have any superusers defined.
Would you like to create one now? (yes/no): no
(trydjango18)LMDV-WHAN:src whan$ python manage.py createsuperuser
Username (leave blank to use 'whan'): whan
Email address: whan@gmail.com
Password: 
Password (again): 
Superuser created successfully.
(trydjango18)LMDV-WHAN:src whan$ python manage.py startapp newsletter
---------
Template Config
#INSTALLED_APPS tempaltes
1, create templates folder under newsletter, and add home.html under templates folder.
2, add 'newsletter', to INSTALLED_APPS in settings.py
#TEMPLATES templates
1, move templates folder to under src
2, add os.path.join(BASE_DIR, "templates") to TEMPLATES.DIRS as 'DIRS': [os.path.join(BASE_DIR, "templates")],
#Launch server
python manage.py runserver
#View
http://127.0.0.1:8000/
---------
Models
1, Add following code to models.py in newsletter folder
class SignUp(models.Model):
    email = models.EmailField()
    full_name = models.CharField(max_length=120, blank=True, null=True)
    timestamp = models.DateTimeField(auto_now_add=True, auto_now=False)
    updated = models.DateTimeField(auto_now_add=False, auto_now=True)
    def __unicode__(self):
        return self.email
2, add 'newsletter', to INSTALLED_APPS in settings.py so newsletter app is part of project
3, python manage.py makemigrations
4, python manage.py migrate
---------
(trydjango18)LMDV-WHAN:src whan$ python manage.py makemigrations
Migrations for 'newsletter':
  0001_initial.py:
    - Create model SignUp
(trydjango18)LMDV-WHAN:src whan$ python manage.py migrate
Operations to perform:
  Synchronize unmigrated apps: staticfiles, messages
  Apply all migrations: admin, contenttypes, newsletter, auth, sessions
Synchronizing apps without migrations:
  Creating tables...
    Running deferred SQL...
  Installing custom SQL...
Running migrations:
  Rendering model states... DONE
  Applying newsletter.0001_initial... OK
---------
Admin
1, add following code to admin.py
from .models import SignUp
class SignUpAdmin(admin.ModelAdmin):
    list_display = ["__unicode__", "timestamp", "updated"]
    class Meta:
        model = SignUp
admin.site.register(SignUp, SignUpAdmin)
2, Visit http://127.0.0.1:8000/admin/
---------
(trydjango18)LMDV-WHAN:src whan$ python manage.py runserver
Performing system checks...
System check identified no issues (0 silenced).
October 03, 2015 - 05:06:17
Django version 1.8.4, using settings 'src.settings'
Starting development server at http://127.0.0.1:8000/
Quit the server with CONTROL-C.
[03/Oct/2015 05:09:25] "GET / HTTP/1.1" 200 1767
```

## View

> launch browser and go to http://127.0.0.1:8000/

> http://127.0.0.1:8000/admin/
