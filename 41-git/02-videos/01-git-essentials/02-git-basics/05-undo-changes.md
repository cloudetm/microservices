# Undo changes

## undo file changes

> modify `README.md`

```
$ cat README.md
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.

This is a line in the file I do not want.

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git diff
diff --git a/README.md b/README.md
index d47eae0..b35f75d 100644
--- a/README.md
+++ b/README.md
@@ -4,3 +4,5 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
+This is a line in the file I do not want.
+
```

> undo changes

```
$ git reset --hard
HEAD is now at dc10f5b delete line I forgot about

$ git status
On branch master
nothing to commit, working tree clean

$ cat README.md 
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.
```

## undo `git commit`

> modify `README.md`

```
$ cat README.md 
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.

Git takes snapshots of the entire repository for us to revert back to.

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .

$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   README.md

$ git diff --staged
diff --git a/README.md b/README.md
index d47eae0..7d64078 100644
--- a/README.md
+++ b/README.md
@@ -4,3 +4,5 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
+Git takes snapshots of the entire repository for us to revert back to.
+

$ git commit -m 'what does git do'
[master 09fed8e] what does git do
 1 file changed, 2 insertions(+)

$ git status
On branch master
nothing to commit, working tree clean
```

> undo `git commit` - 1 commit before

`git reset 09fed8e README.md `

```
$ git log --oneline
09fed8e what does git do
dc10f5b delete line I forgot about
e73c373 I remembered the change
f4dafab what is git
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file
$ git checkout HEAD~1 README.md 
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   README.md

$ cat README.md 
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.

$ git reset 09fed8e README.md 
Unstaged changes after reset:
M	README.md
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")
```

undo changes

```
$ git reset --hard
HEAD is now at 09fed8e what does git do

$ cat README.md 
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.

Git takes snapshots of the entire repository for us to revert back to.

$ git status
On branch master
nothing to commit, working tree clean
```
