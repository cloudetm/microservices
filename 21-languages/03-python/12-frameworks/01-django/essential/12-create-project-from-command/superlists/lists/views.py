from django.http import HttpResponse
def home_page(request):
    response = HttpResponse('home page, request.method=', request.method)
    return response
