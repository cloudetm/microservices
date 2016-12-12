from django.shortcuts import redirect, render
from lists.models import Item
def home_page(request):
    if request.method == 'POST':
        Item.objects.create(text=request.POST['item_text'])  # .create() includes .save()
        return redirect('/lists/foo')  # go to /lists/foo
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items})
def view_list(request):
    print('# view_list() is invoked')
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items})
