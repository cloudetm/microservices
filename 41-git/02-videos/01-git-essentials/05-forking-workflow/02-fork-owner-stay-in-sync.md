# Work with changes and stay in sync

> owner: making changes

```
$ git checkout master
Switched to branch 'master'
Your branch is behind 'origin/master' by 4 commits, and can be fast-forwarded.
  (use "git pull" to update your local branch)
$ git pull
Updating 3ca1ee2..9118bc9
Fast-forward
 collaboration/collaborator.txt | 1 +
 collaboration/offline.txt      | 1 +
 2 files changed, 2 insertions(+)
 create mode 100644 collaboration/collaborator.txt
 create mode 100644 collaboration/offline.txt
 
$ nano collaboration/owner.txt
$ cat collaboration/owner.txt 
I am the owner of this repository!

$ git add .
$ git commit -m 'I am the owner'
[master 420317f] I am the owner
 1 file changed, 2 insertions(+)
 create mode 100644 collaboration/owner.txt

$ git push origin master
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 366 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/pwillhan/git_lesson.git
   9118bc9..420317f  master -> master
```

> collaborator: pull upstream owner repository (ubuntu)

```
$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)

$ git remote add upstream https://github.com/pwillhan/git_lesson.git
$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)
upstream    https://github.com/pwillhan/git_lesson.git (fetch)
upstream    https://github.com/pwillhan/git_lesson.git (push)

$ git pull upstream master
remote: Counting objects: 5, done.
remote: Compressing objects: 100% (2/2), done.
remote: Total 5 (delta 2), reused 5 (delta 2), pack-reused 0
Unpacking objects: 100% (5/5), done.
From https://github.com/pwillhan/git_lesson
 * branch        	master 	-> FETCH_HEAD
 * [new branch]  	master 	-> upstream/master
Updating 20e1354..420317f
Fast-forward
 collaboration/owner.txt | 2 ++
 1 file changed, 2 insertions(+)
 create mode 100644 collaboration/owner.txt

$ git push origin master
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 5, done.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (5/5), 605 bytes | 0 bytes/s, done.
Total 5 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
   20e1354..420317f  master -> master
```
