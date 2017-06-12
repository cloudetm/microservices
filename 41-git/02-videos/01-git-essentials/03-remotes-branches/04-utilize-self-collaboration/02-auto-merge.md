# auto merge

> local changes: change 3 to 4 on `src/my_square.py`, add, commit

```
$ git diff
diff --git a/src/my_square.py b/src/my_square.py
index a5ac408..61c7ac9 100644
--- a/src/my_square.py
+++ b/src/my_square.py
@@ -1,5 +1,5 @@
 def my_square(x):
   return(x ** 2)
 
-print(my_square(3))
+print(my_square(4))

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   src/my_square.py

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .

$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   src/my_square.py

$ git commit -m 'changed 3 to 4'
[master a50fa0e] changed 3 to 4
 1 file changed, 1 insertion(+), 1 deletion(-)

$ git log --oneline
a50fa0e changed 3 to 4
4981ec2 python script to square 3
09fed8e what does git do
dc10f5b delete line I forgot about
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file
```

> github changes: simulate the same file is modify by other person

1. Go to https://github.com/pwillhan/git_lesson/edit/master/src/my_square.py
2. Edit file, add comment `"""uses the ** operator"""` at line 2
3. Add commit changes comment `added documentation about the ** operator`
4. Click `Commit changes`

> auto merging by using `git pull`

accept the default commit message

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
   4981ec2..7d8682c  master     -> origin/master
Auto-merging src/my_square.py
Merge made by the 'recursive' strategy.
 src/my_square.py | 1 +
 1 file changed, 1 insertion(+)
 
$ cat src/my_square.py 
def my_square(x):
  """uses the ** operator"""
  return(x ** 2)

print(my_square(4))

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

$ git push origin master
Counting objects: 8, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (6/6), done.
Writing objects: 100% (8/8), 758 bytes | 0 bytes/s, done.
Total 8 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/pwillhan/git_lesson.git
   7d8682c..ddbcc46  master -> master
```

