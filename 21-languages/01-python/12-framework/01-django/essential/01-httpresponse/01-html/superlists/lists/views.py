from django.http import HttpResponse
def home_page(request):
    response = HttpResponse('<html><title>To-Do lists</title><b>To-Do lists</b></html>')
    return response
