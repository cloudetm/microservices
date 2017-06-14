# Forking and making changes on a Git Flow repository

Development branch

> owner: create `development` branch

```
$ git branch -a
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
LMDV-WHAN:git_lesson whan$ git checkout -b development
Switched to a new branch 'development'
LMDV-WHAN:git_lesson whan$ git push origin development
Total 0 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      development -> development
```

> github owner: verify `development` branch is there

1. Go to https://github.com/pwillhan/git_lesson/branches
2. Verify the `development` branch is there

> github collaborator: Fork repository

1. Login as collaborator `gwillhan`
2. Go to https://github.com/pwillhan/git_lesson
3. Click `Fork` button

> collaborator: making changes at `feature` branch `feature/git_flow_collaboration`

```
$ git clone https://github.com/gwillhan/git_lesson.gitCloning into 'git_lesson'...
remote: Counting objects: 102, done.
remote: Compressing objects: 100% (62/62), done.
remote: Total 102 (delta 21), reused 99 (delta 18), pack-reused 0
Receiving objects: 100% (102/102), 10.21 KiB | 0 bytes/s, done.
Resolving deltas: 100% (21/21), done.
Checking connectivity... done.

$ git branch -a
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/development
  remotes/origin/master
  
$ git checkout -b development
Switched to a new branch 'development'

$ git pull origin development
From https://github.com/gwillhan/git_lesson
 * branch        	development -> FETCH_HEAD
Already up-to-date.
 
$ git log --oneline --all --decorate --graph
*   3d93dca (HEAD -> development, origin/master, origin/HEAD, master) Merge pull request #9 from pwillhan/owner_change
|\  
| * a7a27c2 owner changes
|/  
*   602f253 (origin/development) Merge pull request #8 from gwillhan/collaboration_edit
|\  
| * c0aeebe collaborating on a branch
|/  
* 420317f I am the owner
*   9118bc9 Merge pull request #7 from gwillhan/master
|\  
| * 20e1354 Create collaborator.txt
|/  
*   c9a1907 Merge pull request #6 from pwillhan/offline_change
|\  
| * 737f212 changes without pulling collaborator code
|/  
* 3ca1ee2 Create README.md
* 1617077 readme and folder for collaboration
*   6eca3f6 Merge pull request #5 from pwillhan/development
|\  
| * 620732c added bin dir with readme
| * 3218638 add cube function
|/  
*   ff00376 Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 fix bugs
|/  
*   1f2519e Merge pull request #3 from pwillhan/development
|\  
| *   1aaae8d Merge pull request #2 from pwillhan/feature/document_docs
| |\  
|/ /  
| * 62caca6 added docs to doc
|/  
*   eca1130 Merge pull request #1 from pwillhan/document_src_functions
|\  
| * 710582b add and update documentation for squares
|/  
* 2d7b5d6 added a second function for calculating square

$ nano collaboration/collaborator.txt
$ cat collaboration/collaborator.txt
i am a collaborator
 
This is a change I am making.
 
git-flow is an extension of working with branches.
 
$ git checkout -b feature/git_flow_collaboration
M    collaboration/collaborator.txt
Switched to a new branch 'feature/git_flow_collaboration'

$ git add .
$ git commit -m 'git-flow collaboration'
[feature/git_flow_collaboration 55a6f2f] git-flow collaboration
 1 file changed, 3 insertions(+)

$ git push origin feature/git_flow_collaboration
Username for 'https://github.com': gwillhan
Password for 'https://gwillhan@github.com':
Counting objects: 4, done.
Compressing objects: 100% (4/4), done.
Writing objects: 100% (4/4), 409 bytes | 0 bytes/s, done.
Total 4 (delta 2), reused 0 (delta 0)
remote: Resolving deltas: 100% (2/2), completed with 2 local objects.
To https://github.com/gwillhan/git_lesson.git
 * [new branch]  	feature/git_flow_collaboration -> feature/git_flow_collaboration
```

> github collaborator: pull request from feature branch to owner `development` branch

1. Go to https://github.com/gwillhan/git_lesson
2. Click `Compare & pull request`
3. Select `base: development` on left side (owner)
4. Click `Create pull request`

> github owner: merge forked pull request into owner `development` branch

1. Go to https://github.com/pwillhan/git_lesson/pulls
2. Click `Feature/git flow collaboration`
3. Click `Merge pull request`, click `Confirm merge`
