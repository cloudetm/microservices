# Incorporate changes in existing branches

> github: simulate other collaborator to making changes

1. Login as other collaborator
2. Go to https://github.com/pwillhan/git_lesson/edit/master/collaboration/README.md
3. Edit file, add below, click `Commit changes`
added by other collaborator.

> local: making changes

```
$ nano collaboration/offline.txt
$ cat collaboration/offline.txt 
This is something I am creating without pulling in my collaborators's changes.

$ git checkout -b offline_change
Switched to a new branch 'offline_change'

$ git status
On branch offline_change
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	collaboration/offline.txt

nothing added to commit but untracked files present (use "git add" to track)

$ git add .
$ git commit -m 'changes without pulling collaborator code'
[offline_change cb5fd0b] changes without pulling collaborator code
 1 file changed, 1 insertion(+)
 create mode 100644 collaboration/offline.txt

$ git status
On branch offline_change
nothing to commit, working tree clean
```

> `feature` branch rebase changes from `master`

```
$ git checkout master
Switched to branch 'master'
Your branch is up-to-date with 'origin/master'.

$ git pull origin master
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 4 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   1617077..3ca1ee2  master     -> origin/master
Updating 1617077..3ca1ee2
Fast-forward
 collaboration/README.md | 2 ++
 1 file changed, 2 insertions(+)

$ cat collaboration/README.md 
added by other collaborator.

files and folder used for collaboration

$ git checkout offline_change
Switched to branch 'offline_change'

$ git rebase master
First, rewinding head to replay your work on top of it...
Applying: changes without pulling collaborator code

$ cat collaboration/README.md 
added by other collaborator.

files and folder used for collaboration

$ git status
On branch offline_change
nothing to commit, working tree clean

$ git push origin offline_change
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 437 bytes | 0 bytes/s, done.
Total 4 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      offline_change -> offline_change
```

> github - merge `feature` branch into `master`

```
1. Go to https://github.com/pwillhan/git_lesson
2. Enter `Add documentation`, click `Compare & pull request` for `offline_change`
3. Click `Create pull request`
4. Click `Merge pull request`, `Confirm merge`, `Delete branch`
```
