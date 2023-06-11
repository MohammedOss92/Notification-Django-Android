from django.urls import path
from . import views

urlpatterns = [
    path('send-notification/', views.send_notification, name='send-notification'),
    path('send-notification-page/', views.send_notification_page, name='send-notification-page'),
]
