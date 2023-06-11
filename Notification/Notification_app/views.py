from django.shortcuts import render
from django.http import HttpResponse
import requests
import json

# def send_notification(request):
#     url = 'https://fcm.googleapis.com/fcm/send'
#     headers = {
#         'Authorization': 'key=AAAAfHFKZCI:APA91bEti4-0gCmWqMr_WMR-PbFFbgOYZ3Gd-rj_yTWxRCGwwBuRRWgLSvlOZAm6NvdkEPQjAEdhooXmZBuHwT7fbfw0rmFTkqk5jR28BdM0r-roLgtSU4wbRNtyNXHnbcaqO-fIP6TN',
#         'Content-Type': 'application/json'
#     }
#     payload = {
#         'to': '/topics/alert',
#         'notification': {
#             'title': 'Something',
#             'body': 'New for you'
#         },
#         'data': {
#             'data': 'get',
#             'pranay': 'pranay',
#             'image': 'https://www.webrooper.com/androiddb/uploads/12.jpeg',
#             'tag': 'image'
#         }
#     }
#     response = requests.post(url, headers=headers, data=json.dumps(payload))
#     return HttpResponse(response.text)


def send_notification_page(request):
    return render(request, 'send_notification.html')

def send_notification(request):
    if request.method == 'POST':
        url = 'https://fcm.googleapis.com/fcm/send'
        headers = {
            'Authorization': 'key=AAAAXn7NPeI:APA91bGxaX8yYD0FYJMBIpt6hcW1b4Z6qyBc7tadKdB8BqbK0B5ckJ9DfDwjBNyijuKMpzOxrsTOhgFMTEn_f1xAghPyBZYLRfWNBtNCsDSBS_N0mmRJm9bEn1FV4ZS14OoFxPwkLgZJ',
            'Content-Type': 'application/json'
        }
        payload = {
            'to': '/topics/alert',
            'notification': {
                'title': request.POST.get('title', ''),
                'body': request.POST.get('body', '')
            },
            'data': {
                'data': 'get',
                'pranay': 'pranay',
                'image': 'https://www.webrooper.com/androiddb/uploads/12.jpeg',
                'tag': 'image'
            }
        }
        response = requests.post(url, headers=headers, data=json.dumps(payload))
        return HttpResponse(response.text)
    else:
        return HttpResponse('Method Not Allowed')
