```
django-admin startproject nwc_project
cd nwc_project
python manage.py startapp nwc_app 
```


## Add the new app to your installed apps. In nwc_project/settings.py, add 'nwc_app' to the INSTALLED_APPS list:

```
INSTALLED_APPS = [
    # ...
    'nwc_app',
]
```

