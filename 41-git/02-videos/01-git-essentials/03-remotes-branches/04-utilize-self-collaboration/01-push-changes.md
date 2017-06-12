# push changes

> make changes

```
$ mkdir src
$ cd src
$ nano my_square.py

$ cat my_square.py 
def my_square(x):
  return(x ** 2)

print(my_square(3))

$ python my_square.py 
9
```

> git add, commit, push

```
$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	src/

nothing added to commit but untracked files present (use "git add" to track)

$ git add .

$ git commit -m 'python script to square 3'
[master 4981ec2] python script to square 3
 1 file changed, 5 insertions(+)
 create mode 100644 src/my_square.py
 
$ git push origin master
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 383 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
   09fed8e..4981ec2  master -> master
```

checkout https://github.com/pwillhan/git_lesson/blob/master/src/my_square.py

