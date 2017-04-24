# Setup

https://www.safaribooksonline.com/library/view/learning-path-introduction/9781491958018/video227640.html

## mac

> install python

```
$ brew install python
$ brew install python3

$ python --version
Python 2.7.10
$ python3 --version
Python 3.5.0
```

> install virtualenv

```
$ sudo easy_install virtualenv
```

> activate virtualenv

python 2.7

```
$ virtualenv venv
New python executable in venv/bin/python
Installing setuptools, pip, wheel...done.

$ source venv/bin/activate
(venv) $ which python
/Users/whan/python/venv/bin/python
```

python 3

```
$ python3 -m venv venv

$ source venv/bin/activate
(venv) $ which python
/Users/whan/python/venv/bin/python
```
