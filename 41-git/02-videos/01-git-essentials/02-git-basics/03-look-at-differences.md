# Look at differences

## `git diff`

> modify `README.md`

```
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.
```

> `git diff`

```
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git diff
diff --git a/README.md b/README.md
index f4692df..d47eae0 100644
--- a/README.md
+++ b/README.md
@@ -2,3 +2,5 @@ This repository contains the files used for a git lesson.
 
 This is a change to an existing file that git has already tracked.
 
+Git is a version control system that prevents us from having the "final doc" problem.
+

$ git add .

$ git commit -m 'what is git'
[master f4dafab] what is git
 1 file changed, 2 insertions(+)

$ git status
On branch master
nothing to commit, working tree clean
```

## `git diff --staged`

> modify `README.md`

```
$ cat README.md
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.

Git is a version control system that prevents us from having the "final doc" problem.

This is a commit that I am not aware of, or one that I have forgotten about.
```

> `git diff --staged`

```
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

	modified:   README.md

$ git add .

$ git diff --staged
diff --git a/README.md b/README.md
index d47eae0..5c0cadc 100644
--- a/README.md
+++ b/README.md
@@ -4,3 +4,5 @@ This is a change to an existing file that git has already tracked.
 
 Git is a version control system that prevents us from having the "final doc" problem.
 
+This is a commit that I am not aware of, or one that I have forgotten about.
+

$ git commit -m 'I remembered the change'
[master e73c373] I remembered the change
 1 file changed, 2 insertions(+)
```
