# Staying in sync with conflicts

## in sync `development` branch in both owner and collaborator

> owner `development` branch

```
$ git checkout development
Switched to branch 'development'

$ git pull origin development
From https://github.com/pwillhan/git_lesson
 * branch            development -> FETCH_HEAD
Already up-to-date.
```

> collaborator `development` branch

```
$ git checkout development
Already on 'development'

$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)
upstream    https://github.com/pwillhan/git_lesson.git (fetch)
upstream    https://github.com/pwillhan/git_lesson.git (push)

$ git pull upstream development
From https://github.com/pwillhan/git_lesson
 * branch        	development -> FETCH_HEAD
Already up-to-date.

$ git push origin development
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Everything up-to-date
```

## collaborator `development` branch rebase conflicts from owner `development` branch 

> owner: making changes causes conflicts

```
$ git checkout development
Switched to branch 'development'

$ nano collaboration/collaborator.txt 
$ git diff
diff --git a/collaboration/collaborator.txt b/collaboration/collaborator.txt
index a2d1d48..8ef3a04 100644
--- a/collaboration/collaborator.txt
+++ b/collaboration/collaborator.txt
@@ -1,3 +1,6 @@
+# A file used by the collaborator
+an edit made by the owner.
+
 i am a collaborator
 
 This is a change I am making.
 
$ git add .
$ git commit -m 'owner made changes in development branch'
[development a7aa04a] owner made changes in development branch
 1 file changed, 3 insertions(+)

$ git push origin development
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 457 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/pwillhan/git_lesson.git
   b796708..a7aa04a  development -> development
```

> collaborator: making changes on the same place as owner

```
$ git checkout -b feature/collab_changes
Switched to a new branch 'feature/collab_changes'

$ nano collaboration/collaborator.txt

$ cat collaboration/collaborator.txt
# Collaboration
 
An edit made by the collaborator.
 
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches.

$ git add .
$ git commit -m 'collab changes'
[feature/collab_changes eb7360b] collab changes
 1 file changed, 4 insertions(+)

$ git push origin feature/collab_changes
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 4, done.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 432 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
 * [new branch]  	feature/collab_changes -> feature/collab_changes
```

### github merge conflicts

> github collaborator: try to merge changes have conflicts

1. Go to https://github.com/gwillhan/git_lesson
2. Click `Compare & pull request`
3. Select owner `base:development`; Result: `Can't automatically merge` error occurs
4. Click `Create pull request`

> github owner: merge conflicts

1. Go to https://github.com/pwillhan/git_lesson/pulls
2. Click `collab changes`; Result: `This branch has conflicts that must be resolved`

### resolve conflicts by rebase

> collaborator: pull upstream development before rebase

```
$ git checkout development
Switched to branch 'development'
$ git pull upstream development
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 4 (delta 2), reused 4 (delta 2), pack-reused 0
Unpacking objects: 100% (4/4), done.
From https://github.com/pwillhan/git_lesson
 * branch        	development -> FETCH_HEAD
   b796708..a7aa04a  development -> upstream/development
Updating b796708..a7aa04a
Fast-forward
 collaboration/collaborator.txt | 3 +++
 1 file changed, 3 insertions(+)
```

> collaborator: feature branch rebase development

1. $ git rebase development
2. $ git rebase --continue
3. $ git rebase --skip

```
$ git checkout feature/collab_changes
Switched to branch 'feature/collab_changes'

$ git rebase development
First, rewinding head to replay your work on top of it...
Applying: collab changes
Using index info to reconstruct a base tree...
M    collaboration/collaborator.txt
Falling back to patching base and 3-way merge...
Auto-merging collaboration/collaborator.txt
CONFLICT (content): Merge conflict in collaboration/collaborator.txt
error: Failed to merge in the changes.
Patch failed at 0001 collab changes
The copy of the patch that failed is found in: .git/rebase-apply/patch
 
When you have resolved this problem, run "git rebase --continue".
If you prefer to skip this patch, run "git rebase --skip" instead.
To check out the original branch and stop rebasing, run "git rebase --abort".

$ cat collaboration/collaborator.txt
<<<<<<< a7aa04a78d47f0760bf848c67cd3c28be18f0e6b
# A file used by the collaborator
an edit made by the owner.
=======
# Collaboration
 
An edit made by the collaborator.
>>>>>>> collab changes
 
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches.
  
$ nano collaboration/collaborator.txt
$ cat collaboration/collaborator.txt
# Collaboration
 
An edit made by the collaborator.
 
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches. 
 
$ git status
rebase in progress; onto a7aa04a
You are currently rebasing branch 'feature/collab_changes' on 'a7aa04a'.
  (fix conflicts and then run "git rebase --continue")
  (use "git rebase --skip" to skip this patch)
  (use "git rebase --abort" to check out the original branch)
 
Unmerged paths:
  (use "git reset HEAD <file>..." to unstage)
  (use "git add <file>..." to mark resolution)
 
    both modified:   collaboration/collaborator.txt
 
no changes added to commit (use "git add" and/or "git commit -a")

$ git add .
$ git commit -m 'fix conflicts'
[detached HEAD 4423cf7] fix conflicts
 1 file changed, 3 insertions(+), 2 deletions(-)

$ git status
rebase in progress; onto a7aa04a
You are currently rebasing branch 'feature/collab_changes' on 'a7aa04a'.
  (all conflicts fixed: run "git rebase --continue")
 
nothing to commit, working directory clean

$ git rebase --continue
Applying: collab changes
No changes - did you forget to use 'git add'?
If there is nothing left to stage, chances are that something else
already introduced the same changes; you might want to skip this patch.
 
When you have resolved this problem, run "git rebase --continue".
If you prefer to skip this patch, run "git rebase --skip" instead.
To check out the original branch and stop rebasing, run "git rebase --abort".
 
$ cat collaboration/collaborator.txt
# Collaboration
 
An edit made by the collaborator.
 
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches.
 
$ git rebase --skip

$ git push -f origin feature/collab_changesUsername for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 8, done.
Compressing objects: 100% (8/8), done.
Writing objects: 100% (8/8), 858 bytes | 0 bytes/s, done.
Total 8 (delta 4), reused 0 (delta 0)
remote: Resolving deltas: 100% (4/4), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
 + eb7360b...4423cf7 feature/collab_changes -> feature/collab_changes (forced update)
```

> github owner: merge changes after rebase

1. Go to https://github.com/pwillhan/git_lesson/pulls
2. Click `collab changes`
3. Click `Merge pull request`, click `Confirm merge`

> github owner: merge `development` into `master`

1. Go to https://github.com/pwillhan/git_lesson
2. For `development` branch, click `Compare & pull request`
3. Click `Create pull request`
4. Click `Merge pull request`, click `Confirm merge`
