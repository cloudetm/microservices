from django.shortcuts import redirect, render
from lists.models import Item, List
from django.http import HttpResponse

def home_page(request):
    return render(request, 'home.html')

def new_list(request):
    print('new_list')
    list_ = List.objects.create()
    Item.objects.create(text=request.POST['item_text'], list=list_)
    return redirect('/lists/%d/' % (list_.id))

def view_list(request, list_id):
    print('view_list')
    list_ = List.objects.get(id=list_id)
    return render(request, 'list.html', {'list': list_})

def add_item(request, list_id):
    print('add_item')
    list_ = List.objects.get(id=list_id)
    Item.objects.create(text=request.POST['item_text'], list=list_)
    return redirect('/lists/%d' % list_.id)
