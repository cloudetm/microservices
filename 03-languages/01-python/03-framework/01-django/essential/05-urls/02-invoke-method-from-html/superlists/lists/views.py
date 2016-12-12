from django.shortcuts import render
from lists.models import Item
def home_page(request):
    return render(request, 'home.html')
def new_list(request):
    print('# new_list() is invoked')
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items})
