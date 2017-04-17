from django.http import HttpResponse
from django.shortcuts import redirect
def home_page(request):
    return redirect('/lists/foo')
def new_list(request):
    return HttpResponse('new_list is invoked')