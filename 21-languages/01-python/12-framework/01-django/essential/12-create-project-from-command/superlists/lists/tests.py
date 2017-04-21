from django.test import TestCase

class GetTest(TestCase):
    def test_client_get_request(self):
        response = self.client.get('/')
        print('# response.status_code=', response.status_code)
