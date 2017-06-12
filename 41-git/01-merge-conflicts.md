# merge conflicts

> local changes: x -> y and square 42

```
$ cat src/my_square.py 
def my_square(y):
  """uses the ** operator"""
  return(y ** 2)

print(my_square(42))

$ git diff
diff --git a/src/my_square.py b/src/my_square.py
index e17609d..56edaf7 100644
--- a/src/my_square.py
+++ b/src/my_square.py
@@ -1,6 +1,6 @@
-def my_square(x):
+def my_square(y):
   """uses the ** operator"""
-  return(x ** 2)
+  return(y ** 2)
 
-print(my_square(4))
+print(my_square(42))
```

> github changes: x -> z and square 24

Edit file

```
def my_square(z):
  """uses the * operator"""
  return(z * z)

print(my_square(24))
```

Click `Commit changes`

> resolve conflicts

```
$ git push origin master
To https://github.com/pwillhan/git_lesson.git
 ! [rejected]        master -> master (fetch first)
error: failed to push some refs to 'https://github.com/pwillhan/git_lesson.git'
hint: Updates were rejected because the remote contains work that you do
hint: not have locally. This is usually caused by another repository pushing
hint: to the same ref. You may want to first integrate the remote changes
hint: (e.g., 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

$ git pull origin master
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 4 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   ddbcc46..0125554  master     -> origin/master
Auto-merging src/my_square.py
CONFLICT (content): Merge conflict in src/my_square.py
Automatic merge failed; fix conflicts and then commit the result.
```

modify the file to keep local changes

```
$ cat src/my_square.py 
def my_square(y):
  """uses the ** operator"""
  return(y ** 2)

print(my_square(42))

$ git status
On branch master
You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)

	both modified:   src/my_square.py

no changes added to commit (use "git add" and/or "git commit -a")

$ git commit -m 'fix merge conflict'
[master d76b722] fix merge conflict

$ git push origin master
Counting objects: 5, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (5/5), 508 bytes | 0 bytes/s, done.
Total 5 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), done.
To https://github.com/pwillhan/git_lesson.git
   0125554..d76b722  master -> master

```
