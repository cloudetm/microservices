# docker run mac

```
mkdir dockerrun
cd dockerrun/

vi app.py
from flask import Flask
app = Flask(__name__)
@app.route('/')
def hello_world():
    return 'Hello World from python flask!\n'
if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)

vi Dockerfile
FROM python:3.4-alpine
ADD . /code
WORKDIR /code
RUN pip install flask
CMD ["python", "app.py"]

docker build -t myservice .

docker run -p 80:5000 -d myservice

curl http://localhost:80

docker ps

docker ps -a
```
