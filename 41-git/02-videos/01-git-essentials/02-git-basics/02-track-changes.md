# 2.2 Track changes (add, commit, log) 

> add `README.md`

```
This repository contains the files used for a git lesson.
```

> `git add`, `git commit`, `git log`

```
$ git status
On branch master

Initial commit

Untracked files:
  (use "git add <file>..." to include in what will be committed)

	README.md

nothing added to commit but untracked files present (use "git add" to track)

$ git add README.md 

$ git status
On branch master

Initial commit

Changes to be committed:
  (use "git rm --cached <file>..." to unstage)

	new file:   README.md

$ git commit -m 'initial commit with readme.md file'
[master (root-commit) cb9ef0b] initial commit with readme.md file
 1 file changed, 1 insertion(+)
 create mode 100644 README.md
LMDV-WHAN:git_lesson whan$ nano README.md 
LMDV-WHAN:git_lesson whan$ git status
On branch master
nothing to commit, working tree clean

$ git log
commit cb9ef0b6bee26eab9d89ad8c17c6ac64032e9619
Author: William Han <pwillhan@gmail.com>
Date:   Fri Jun 9 21:42:40 2017 -0700

    initial commit with readme.md file
```

> change `README.md`

```
$ cat README.md
This repository contains the files used for a git lesson.

This is a change to an existing file that git has already tracked.
```

```
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .

$ git commit -m "a second commit to the readme file"
[master 3b05fae] a second commit to the readme file
 1 file changed, 3 insertions(+)

$ git log --oneline
3b05fae a second commit to the readme file
cb9ef0b initial commit with readme.md file

$ git status
On branch master
nothing to commit, working tree clean
```
