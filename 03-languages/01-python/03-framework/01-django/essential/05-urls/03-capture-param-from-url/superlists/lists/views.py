from django.shortcuts import redirect
from django.http import HttpResponse
def home_page(request):
    return redirect('/lists/18')
def view_list(request, list_id):
    return HttpResponse('list_id=' + list_id)
