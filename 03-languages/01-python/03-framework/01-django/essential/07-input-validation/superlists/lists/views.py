from django.shortcuts import render
from django.core.exceptions import ValidationError
from lists.models import Item
def home_page(request):
    error = None
    if request.method == 'POST':
        item = Item(text=request.POST['item_text'])
        try:
            item.full_clean()
            item.save()
        except ValidationError:
            error = "You can't have an empty list item"
    items = Item.objects.all()
    return render(request, 'home.html', {'items': items, 'error': error})
