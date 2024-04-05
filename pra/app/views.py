from django.shortcuts import render, redirect, HttpResponse
from .forms import UserForm
from .models import User
# Create your views here.
def home(request):
    return render(request,"home.html",{"name":"Anuj"})

def save(request):
    if request.method == "POST":
        form = UserForm(request.POST)
        if form.is_valid(): 
            user = User()
            user.user_name = form.cleaned_data["user_name"]
            user.email = form.cleaned_data["email"]
            user.password = form.cleaned_data["password"]
            user.save()
            return redirect("/home/")
        return HttpResponse("Invalid Data")
    else:
        return render(request,"form.html",{"form":UserForm()})
