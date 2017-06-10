# Look around

> 1 commit before

```
$ git diff HEAD~1
diff --git a/README.md b/README.md
index d47eae0..5c0cadc 100644
--- a/README.md
+++ b/README.md
@@ -4,3 +4,5 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
+This is a commit that I am not aware of, or one that I have forgotten about.
+
```

> 2 state behind

```
$ git diff HEAD~2
diff --git a/README.md b/README.md
index f4692df..5c0cadc 100644
--- a/README.md
+++ b/README.md
@@ -2,3 +2,7 @@ This repository contains the files used for a git lesson.
 
 This is a change to an existing file that git has already tracked.
 
+Git is a version control system that prevents us from having the "final doc" problem.
+
+This is a commit that I am not aware of, or one that I have forgotten about.
+
```

> diff second commit

```
$ git log --oneline
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file

$ git diff 3b05fae
diff --git a/README.md b/README.md
index f4692df..5c0cadc 100644
--- a/README.md
+++ b/README.md
@@ -2,3 +2,7 @@ This repository contains the files used for a git lesson.
 
 This is a change to an existing file that git has already tracked.
 
+Git is a version control system that prevents us from having the "final doc" problem.
+
+This is a commit that I am not aware of, or one that I have forgotten about.
+
```

> revert file to previous state

```
$ git checkout HEAD~1 README.md 

$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   README.md

$ git diff --staged
diff --git a/README.md b/README.md
index 5c0cadc..d47eae0 100644
--- a/README.md
+++ b/README.md
@@ -4,5 +4,3 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
-This is a commit that I am not aware of, or one that I have forgotten about.
-

$ git commit -m 'delete line I forgot about'
[master dc10f5b] delete line I forgot about
 1 file changed, 2 deletions(-)

$ git log --oneline
dc10f5b delete line I forgot about
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file
```

> go back to the last state

go to the first commit

```
$ git checkout cb9ef0b
Note: checking out 'cb9ef0b'.

You are in 'detached HEAD' state. You can look around, make experimental
changes and commit them, and you can discard any commits you make in this
state without impacting any branches by performing another checkout.

If you want to create a new branch to retain commits you create, you may
do so (now or later) by using -b with the checkout command again. Example:

  git checkout -b <new-branch-name>

HEAD is now at cb9ef0b... initial commit with readme.md file
```

go back the last state `git checkout master`

```
$ git checkout master
Previous HEAD position was cb9ef0b... initial commit with readme.md file
Switched to branch 'master'
LMDV-WHAN:git_lesson whan$ git status
On branch master
nothing to commit, working tree clean
```

> undo delete file

```
$ rm README.md

$ git status
On branch master
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	deleted:    README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git checkout master README.md

$ git status
On branch master
nothing to commit, working tree clean

$ git log --oneline
dc10f5b delete line I forgot about
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file
```

> undo `git commit` - 1 commit before

`git reset HAED README.md`

```
$ git checkout HEAD~1 README.md

$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   README.md

$ git diff --staged
diff --git a/README.md b/README.md
index d47eae0..5c0cadc 100644
--- a/README.md
+++ b/README.md
@@ -4,3 +4,5 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
+This is a commit that I am not aware of, or one that I have forgotten about.
+

$ git reset HEAD README.md 
Unstaged changes after reset:
M	README.md

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git checkout .

$ git status
On branch master
nothing to commit, working tree clean
```
