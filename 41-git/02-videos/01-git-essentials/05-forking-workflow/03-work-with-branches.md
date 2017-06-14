# Work with branches

> collaborator: making changes on a branch

```
$ git checkout -b collaboration_edit
Switched to a new branch 'collaboration_edit'

$ nano collaboration/collaborator.txt

$ cat collaboration/collaborator.txt
i am a collaborator
 
This is a change I am making.

$ git status
On branch collaboration_edit
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)
 
    modified:   collaboration/collaborator.txt
 
no changes added to commit (use "git add" and/or "git commit -a")

$ git add .
$ git commit -m 'collaborating on a branch'[collaboration_edit c0aeebe] collaborating on a branch
 1 file changed, 2 insertions(+)
 
$ git push origin collaboration_edit
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 4, done.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 375 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
 * [new branch]  	collaboration_edit -> collaboration_edit
```

> github collaborator: create pull request

1. Go to https://github.com/gwillhan/git_lesson
2. Click `Compare & pull request`, add comment, click `Create pull request`

> github owner: 

1. Go to https://github.com/pwillhan/git_lesson/pulls
2. Click `collaborating on a branch`
3. Click `Merge pull request`, click `Confirm merge`

> owner: making changes

```
$ git pull origin master
remote: Counting objects: 5, done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 5 (delta 3), reused 4 (delta 2), pack-reused 0
Unpacking objects: 100% (5/5), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   420317f..602f253  master     -> origin/master
Updating 420317f..602f253
Fast-forward
 collaboration/collaborator.txt | 2 ++
 1 file changed, 2 insertions(+)

$ git checkout -b owner_change
Switched to a new branch 'owner_change'

$ nano collaboration/owner.txt 
$ git diff
diff --git a/collaboration/owner.txt b/collaboration/owner.txt
index 8239562..698caac 100644
--- a/collaboration/owner.txt
+++ b/collaboration/owner.txt
@@ -1,2 +1,3 @@
 I am the owner of this repository!
 
+This is a change on a new branch.

$ git add .
$ git commit -m 'owner changes'
[owner_change a7a27c2] owner changes
 1 file changed, 1 insertion(+)
$ git status
On branch owner_change
nothing to commit, working tree clean

$ git push origin owner_change
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 378 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      owner_change -> owner_change
```

> github owner: merge changes from branch to master

1. Go to https://github.com/pwillhan/git_lesson
2. Click `Compare & pull request`
3. Click `Create pull request`
4. Click `Merge pull request`, click `Confirm merge`

> collaborator: pull upstream owner repository (ubuntu) 

```
$ git checkout master
Switched to branch 'master'
Your branch is up-to-date with 'origin/master'.

$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)
upstream    https://github.com/pwillhan/git_lesson.git (fetch)
upstream    https://github.com/pwillhan/git_lesson.git (push)

$ git pull upstream master
remote: Counting objects: 6, done.
remote: Compressing objects: 100% (4/4), done.
remote: Total 6 (delta 2), reused 6 (delta 2), pack-reused 0
Unpacking objects: 100% (6/6), done.
From https://github.com/pwillhan/git_lesson
 * branch        	master 	-> FETCH_HEAD
   420317f..3d93dca  master 	-> upstream/master
Updating 420317f..3d93dca
Fast-forward
 collaboration/collaborator.txt | 2 ++
 collaboration/owner.txt    	| 1 +
 2 files changed, 3 insertions(+)

$ git push origin master
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 6, done.
Compressing objects: 100% (6/6), done.
Writing objects: 100% (6/6), 853 bytes | 0 bytes/s, done.
Total 6 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
   420317f..3d93dca  master -> master 
```