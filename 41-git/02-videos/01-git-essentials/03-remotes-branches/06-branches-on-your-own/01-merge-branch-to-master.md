# merge conflict

> create and switch branch, git add and commit changes

```
$ git checkout -b my_branch
Switched to a new branch 'my_branch'

$ git branch -a
  master
* my_branch
  remotes/origin/master

$ nano src/my_square.py 
$ cat src/my_square.py 
def my_square(y):
  """uses the ** operator"""
  return(y ** 2)

def my_square2(x):
  return(x * x)

print(my_square(42))
print(my_square2(42))

$ git status
On branch my_branch
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   src/my_square.py

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .

$ git commit -m 'added a second function for calculating square'
[my_branch 2d7b5d6] added a second function for calculating square
 1 file changed, 4 insertions(+), 1 deletion(-)
```

> view all logs including branches

```
$ git checkout master
Switched to branch 'master'

$ git log --oneline --all
2d7b5d6 added a second function for calculating square
1e87591 fix merge conflict
0125554 x -> z and square 24
e451135 x -> y and square 42
ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
7d8682c added documentation about the ** operator
a50fa0e changed 3 to 4
4981ec2 python script to square 3
09fed8e what does git do
dc10f5b delete line I forgot about
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file
```

> merge branch changes into master

```
$ git status
On branch master
nothing to commit, working tree clean

$ git branch -a
* master
  my_branch
  remotes/origin/master

$ git merge my_branch
Updating 1e87591..2d7b5d6
Fast-forward
 src/my_square.py | 5 ++++-
 1 file changed, 4 insertions(+), 1 deletion(-)

$ cat src/my_square.py 
def my_square(y):
  """uses the ** operator"""
  return(y ** 2)

def my_square2(x):
  return(x * x)

print(my_square(42))
print(my_square2(42))

$ git log --oneline --all --decorate --graph
* 2d7b5d6 (HEAD -> master, my_branch) added a second function for calculating square
*   1e87591 (origin/master) fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> push changes to github

```
$ git push origin master
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 442 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
   1e87591..2d7b5d6  master -> master
```
