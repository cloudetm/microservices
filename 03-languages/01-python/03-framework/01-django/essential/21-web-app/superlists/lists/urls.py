from django.conf.urls import include, url

urlpatterns = [
    url('^(\d+)/$', 'lists.views.view_list', name='view_list'),
    url('^(\d+)/add_item$', 'lists.views.add_item', name='add_item'),
    url(r'^new$', 'lists.views.new_list', name='new_list'),
]
