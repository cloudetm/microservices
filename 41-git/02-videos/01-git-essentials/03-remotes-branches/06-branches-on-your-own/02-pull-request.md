# pull request

> make changes on a new branch

```
$ git checkout -b document_src_functions
Switched to a new branch 'document_src_functions'

$ nano src/my_square.py 
$ git diff
diff --git a/src/my_square.py b/src/my_square.py
index 177914e..f343463 100644
--- a/src/my_square.py
+++ b/src/my_square.py
@@ -1,8 +1,11 @@
 def my_square(y):
-  """uses the ** operator"""
+  """uses the ** operator
+  """
   return(y ** 2)
 
 def my_square2(x):
+  """uses the * operator to calculate square
+  """
   return(x * x)
 
 print(my_square(42))

$ git status
On branch document_src_functions
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   src/my_square.py

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .

$ git commit -m 'add and update documentation for squares'
[document_src_functions 710582b] add and update documentation for squares
 1 file changed, 4 insertions(+), 1 deletion(-)

$ git status
On branch document_src_functions
nothing to commit, working tree clean
```

> push changes to github

```
$ git push origin document_src_functions
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 456 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      document_src_functions -> document_src_functions
```

> github `pull request`

1. Go to https://github.com/pwillhan/git_lesson
2. Click `Compare & pull request`
3. Add comment `added documentation for a second squares function.`
4. Click `Create pull request`
5. Click `Merge pull request`, `Confirm merge`
6. Click `Delete branch`

> pull the changes to `master`

```
$ git checkout master
Switched to branch 'master'

$ git pull origin master
remote: Counting objects: 1, done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   2d7b5d6..eca1130  master     -> origin/master
Updating 2d7b5d6..eca1130
Fast-forward
 src/my_square.py | 5 ++++-
 1 file changed, 4 insertions(+), 1 deletion(-)
```

> clean local git

```
$ git branch -a
  document_src_functions
* master
  my_branch
  remotes/origin/document_src_functions
  remotes/origin/master

$ git fetch --prune
From https://github.com/pwillhan/git_lesson
 - [deleted]         (none)     -> origin/document_src_functions
 
$ git branch -d my_branch
Deleted branch my_branch (was 2d7b5d6).

$ git branch -a
  document_src_functions
* master
  remotes/origin/master
```
