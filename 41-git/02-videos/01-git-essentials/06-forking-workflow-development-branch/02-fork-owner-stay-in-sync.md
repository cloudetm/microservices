# Working with changes and staying in sync

> collaborator: pull `upstream` `development`

```
$ git branch -a
  development
* feature/git_flow_collaboration
  master
  remotes/origin/HEAD -> origin/master
  remotes/origin/development
  remotes/origin/feature/git_flow_collaboration
  remotes/origin/master
  
$ git checkout development
Switched to branch 'development'
$ cat collaboration/collaborator.txt
i am a collaborator
 
This is a change I am making.

$ git pull origin development
From https://github.com/gwillhan/git_lesson
 * branch        	development -> FETCH_HEAD
Already up-to-date.

$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)

$ git remote add upstream https://github.com/pwillhan/git_lesson.git
$ git remote -v
origin    https://github.com/gwillhan/git_lesson.git (fetch)
origin    https://github.com/gwillhan/git_lesson.git (push)
upstream    https://github.com/pwillhan/git_lesson.git (fetch)
upstream    https://github.com/pwillhan/git_lesson.git (push)

$ git pull upstream development
remote: Counting objects: 1, done.
remote: Total 1 (delta 0), reused 1 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From https://github.com/pwillhan/git_lesson
 * branch        	development -> FETCH_HEAD
 * [new branch]  	development -> upstream/development
Updating 3d93dca..b796708
Fast-forward
 collaboration/collaborator.txt | 3 +++
 1 file changed, 3 insertions(+)
$ cat collaboration/collaborator.txt
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches.

$ git push origin development
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 1, done.
Writing objects: 100% (1/1), 282 bytes | 0 bytes/s, done.
Total 1 (delta 0), reused 0 (delta 0)
To https://github.com/gwillhan/git_lesson.git
   602f253..b796708  development -> development
```

> owner: pull down `development` branch changes

```
$ git status
On branch development
nothing to commit, working tree clean

$ git pull origin development
From https://github.com/pwillhan/git_lesson
 * branch            development -> FETCH_HEAD
Updating 602f253..b796708
Fast-forward
 collaboration/collaborator.txt | 3 +++
 collaboration/owner.txt        | 1 +
 2 files changed, 4 insertions(+)

$ cat collaboration/collaborator.txt 
i am a collaborator

This is a change I am making.

git-flow is an extension of working with branches.
```
