# merge feature branch into development branch

```
$ git checkout -b feature/bin_folder
Switched to a new branch 'feature/bin_folder'

$ mkdir bin
$ nano bin/README.md
$ cat bin/README.md 
# bin

Binary files

$ git status
On branch feature/bin_folder
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	bin/

nothing added to commit but untracked files present (use "git add" to track)

$ git add .
$ git commit -m 'added bin dir with readme'
[feature/bin_folder 93bc588] added bin dir with readme
 1 file changed, 4 insertions(+)
 create mode 100644 bin/README.md

$ gla
* 93bc588 (HEAD -> feature/bin_folder) added bin dir with readme
*   1f2519e (origin/master, origin/development, master, development) Merge pull request #3 from pwillhan/development
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file

$ git checkout development
Switched to branch 'development'
$ git merge feature/bin_folder
Updating 1f2519e..93bc588
Fast-forward
 bin/README.md | 4 ++++
 1 file changed, 4 insertions(+)
 create mode 100644 bin/README.md
```

> fix bugs in `master` branch

```
$ git checkout master
Switched to branch 'master'
$ nano docs/README.md 
$ git diff
diff --git a/docs/README.md b/docs/README.md
index e04779d..8c78a2d 100644
--- a/docs/README.md
+++ b/docs/README.md
@@ -2,3 +2,5 @@
 
 This folder contains documentation or manuscripts related to the current repository.
 
+Fix bugs.
+

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   docs/README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git checkout -b hotfix/fix_bugs
M	docs/README.md
Switched to a new branch 'hotfix/fix_bugs'

$ gla
* 93bc588 (feature/bin_folder, development) added bin dir with readme
*   1f2519e (HEAD -> hotfix/fix_bugs, origin/master, origin/development, master) Merge pull request #3 from pwillhan/development
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file

$ git status
On branch hotfix/fix_bugs
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   docs/README.md

no changes added to commit (use "git add" and/or "git commit -a")

$ git add .
$ git commit -m 'fix bugs'
[hotfix/fix_bugs 4342836] fix bugs
 1 file changed, 2 insertions(+)
$ git push origin hotfix/fix_bugs
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 382 bytes | 0 bytes/s, done.
Total 4 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      hotfix/fix_bugs -> hotfix/fix_bugs
```

> github - merge `feature` branch into `development`

```
1. Go to https://github.com/pwillhan/git_lesson
2. Enter `Add documentation`, click `Compare & pull request` for `hotfix/fix_bugs`
3. Select `development` branch as base
4. Click `Create pull request`
5. Click `Merge pull request`, `Confirm merge`, `Delete branch`
```

> pull latest in `master` branch

```
$ git checkout master
Switched to branch 'master'

$ git pull origin master
remote: Counting objects: 1, done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   1f2519e..ff00376  master     -> origin/master
Updating 1f2519e..ff00376
Fast-forward
 docs/README.md | 2 ++
 1 file changed, 2 insertions(+)
 
$ gla
*   ff00376 (HEAD -> master, origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 (origin/hotfix/fix_bugs, hotfix/fix_bugs) fix bugs
|/  
| * 93bc588 (feature/bin_folder, development) added bin dir with readme
|/  
*   1f2519e (origin/development) Merge pull request #3 from pwillhan/development
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> clean up branches

```
$ git fetch --prune
From https://github.com/pwillhan/git_lesson
 - [deleted]         (none)     -> origin/hotfix/fix_bugs

$ git branch -a
  development
  feature/bin_folder
  hotfix/fix_bugs
* master
  remotes/origin/development
  remotes/origin/master

$ git branch -d feature/bin_folder hotfix/fix_bugs
error: The branch 'feature/bin_folder' is not fully merged.
If you are sure you want to delete it, run 'git branch -D feature/bin_folder'.
Deleted branch hotfix/fix_bugs (was 4342836).

$ git branch -D feature/bin_folder
Deleted branch feature/bin_folder (was 93bc588).

$ git branch -a
  development
* master
  remotes/origin/development
  remotes/origin/master

$ gla
*   ff00376 (HEAD -> master, origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 fix bugs
|/  
| * 93bc588 (development) added bin dir with readme
|/  
*   1f2519e (origin/development) Merge pull request #3 from pwillhan/development
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> feature branch - add cube function to `feature/cube_function`

```
$ git checkout -b feature/cube_function
Switched to a new branch 'feature/cube_function'

$ nano src/my_cube.py
$ cat src/my_cube.py 
def my_cube(x):
  """returns the cube of a number
  """
  return x ** 3
print(my_cube(2))

$ python src/my_cube.py 
8

$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	src/my_cube.py

nothing added to commit but untracked files present (use "git add" to track)

$ git add .
$ git commit -m 'add cube function'
[master 3218638] add cube function
 1 file changed, 6 insertions(+)
 create mode 100644 src/my_cube.py
```

> push `development` branch to github

```
$ gla
* 3218638 (HEAD -> master) add cube function
*   ff00376 (origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 fix bugs
|/  
| * 93bc588 (feature/cube_function, development) added bin dir with readme
|/  
*   1f2519e (origin/development) Merge pull request #3 from pwillhan/development
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file

$ git checkout development
Switched to branch 'development'

$ git push origin development
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (4/4), 405 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
   1f2519e..93bc588  development -> development
```

> rebase `development` branch to incorporate bug fixes from `master` branch

*Before rebase - no bug fixes*

```
$ git status
On branch development
nothing to commit, working tree clean

$ cat docs/README.md 
# Documentation

This folder contains documentation or manuscripts related to the current repository.
```

*After rebase - incorporate Fix bugs from master branch*

```
$ git rebase master
First, rewinding head to replay your work on top of it...
Applying: added bin dir with readme

$ cat docs/README.md 
# Documentation

This folder contains documentation or manuscripts related to the current repository.

Fix bugs.
```

> `development` branch local is not the same as remote (github) `development` branch

```
$ gla
* 620732c (HEAD -> development) added bin dir with readme
* 3218638 (master) add cube function
*   ff00376 (origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 fix bugs
|/  
| * 93bc588 (origin/development, feature/cube_function) added bin dir with readme
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> force update remote (github) `development` branch after rebase

```
$ git push origin development
To https://github.com/pwillhan/git_lesson.git
 ! [rejected]        development -> development (non-fast-forward)
error: failed to push some refs to 'https://github.com/pwillhan/git_lesson.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

$ git push -f origin development
Counting objects: 8, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (6/6), done.
Writing objects: 100% (8/8), 747 bytes | 0 bytes/s, done.
Total 8 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), done.
To https://github.com/pwillhan/git_lesson.git
 + 93bc588...620732c development -> development (forced update)
 
$ gla
* 620732c (HEAD -> development, origin/development) added bin dir with readme
* 3218638 (master) add cube function
*   ff00376 (origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
|\  
| * 4342836 fix bugs
|/  
| * 93bc588 (feature/cube_function) added bin dir with readme
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> rebase `feature` branch to incorporate bug fixes from `development` branch

```
$ git checkout feature/cube_function
Switched to branch 'feature/cube_function'

$ git rebase development
First, rewinding head to replay your work on top of it...
$ cat docs/README.md 
# Documentation

This folder contains documentation or manuscripts related to the current repository.

Fix bugs.

$ gla
* 620732c (HEAD -> feature/cube_function, origin/development, development) added bin dir with readme
* 3218638 (master) add cube function
*   ff00376 (origin/master) Merge pull request #4 from pwillhan/hotfix/fix_bugs
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
*   1e87591 fix merge conflict
|\  
| * 0125554 x -> z and square 24
* | e451135 x -> y and square 42
|/  
*   ddbcc46 Merge branch 'master' of https://github.com/pwillhan/git_lesson
|\  
| * 7d8682c added documentation about the ** operator
* | a50fa0e changed 3 to 4
|/  
* 4981ec2 python script to square 3
* 09fed8e what does git do
* dc10f5b delete line I forgot about
* e73c373 I remembered the change
* f4dafab what is git
* 3b05fae a second commit to the readme file
* cb9ef0b initial commit with readme.md file
```

> github - merge `development` branch into `master`

```
1. Go to https://github.com/pwillhan/git_lesson
2. Click `Compare & pull request` for `development` branch
3. Select `master` branch as base
4. Click `Create pull request`
5. Click `Merge pull request`, `Confirm merge`, `Delete branch`
```

> clean up branches

```
$ git checkout master
Switched to branch 'master'

$ git branch -a
  development
  feature/cube_function
* master
  remotes/origin/development
  remotes/origin/feature/cube_function
  remotes/origin/master

$ git fetch --prune
```
