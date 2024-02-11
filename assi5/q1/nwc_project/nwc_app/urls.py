from django import urls
from nwc_app import views 
from django.urls import include, path


urlpatterns = [
        path('', views.welcome, name='welcome'),
]