from django.conf.urls import url
urlpatterns = [
    url(r'^foo/$', 'lists.views.new_list', name='new_list'),
]
