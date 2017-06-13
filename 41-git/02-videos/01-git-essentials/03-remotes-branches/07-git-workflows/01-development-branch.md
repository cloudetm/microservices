# development branch

> create development branch

```
$ git checkout -b development
Switched to a new branch 'development'

$ gla
*   eca1130 (HEAD -> development, origin/master, master) Merge pull request #1 from pwillhan/document_src_functions
|\  
| * 710582b (document_src_functions) add and update documentation for squares
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

> create feature branch

```
$ git checkout -b feature/document_docs
Switched to a new branch 'feature/document_docs'

$ gla
*   eca1130 (HEAD -> feature/document_docs, origin/master, master, development) Merge pull request #1 from pwillhan/document_src_functions
|\  
| * 710582b (document_src_functions) add and update documentation for squares
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

> feature branch: make changes, git add, commit

```
$ mkdir docs
$ cd docs
$ nano README.md
$ cat docs/README.md 
# Documentation

This folder contains documentation or manuscripts related to the current repository.

$ cd ..
$ git status
On branch feature/document_docs
Untracked files:
  (use "git add <file>..." to include in what will be committed)

	docs/

nothing added to commit but untracked files present (use "git add" to track)

$ git add .

$ git commit -m 'added docs to doc'
[feature/document_docs 62caca6] added docs to doc
 1 file changed, 4 insertions(+)
 create mode 100644 docs/README.md

$ git status
On branch feature/document_docs
nothing to commit, working tree clean

$ git branch -a
  development
  document_src_functions
* feature/document_docs
  master
  remotes/origin/master
```

> switch to `development` branch, push `development` branch to github

```
$ git checkout development
Switched to branch 'development'

$ ls
README.md	src

$ git push origin development
Total 0 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      development -> development
```

github - verify the `development` branch is there

> switch to `feature` branch

```
$ git checkout feature/document_docs
Already on 'feature/document_docs'

$ git push origin feature/document_docs
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (3/3), done.
Writing objects: 100% (4/4), 431 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      feature/document_docs -> feature/document_docs
 ```

github - verify the `feature` branch is there

> github - merge `feature` branch into `development` branch

1. Go to https://github.com/pwillhan/git_lesson
2. Click `Compare & pull request` for `feature/document_docs`
3. Select `development` branch as base
4. Click `Create pull request`
5. Click `Merge pull request`, `Confirm merge`, `Delete branch`

> terminal - `git pull origin development`

```
$ git checkout development
Switched to branch 'development'

$ git pull origin development
remote: Counting objects: 1, done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From https://github.com/pwillhan/git_lesson
 * branch            development -> FETCH_HEAD
   eca1130..1aaae8d  development -> origin/development
Updating eca1130..1aaae8d
Fast-forward
 docs/README.md | 4 ++++
 1 file changed, 4 insertions(+)
 create mode 100644 docs/README.md

$ ls docs/
README.md

$ git branch -a
* development
  feature/document_docs
  master
  remotes/origin/development
  remotes/origin/feature/document_docs
  remotes/origin/master
  
$ git fetch --prune
From https://github.com/pwillhan/git_lesson
 - [deleted]         (none)     -> origin/feature/document_docs

$ git branch -a
* development
  feature/document_docs
  master
  remotes/origin/development
  remotes/origin/master

$ git branch -d feature/document_docs
Deleted branch feature/document_docs (was 62caca6).
```

> github - merge `development` to `master`

1. Go to https://github.com/pwillhan/git_lesson
2. Enter `Add documentation`, click `Compare & pull request` for `feature/document_docs`
3. Select `development` branch as base
4. Click `Create pull request`
5. Click `Merge pull request`, `Confirm merge`, `Delete branch`

> `master` is behind

```
$ gla
*   1aaae8d (HEAD -> development, origin/development) Merge pull request #2 from pwillhan/feature/document_docs
|\  
| * 62caca6 added docs to doc
|/  
*   eca1130 (origin/master, master) Merge pull request #1 from pwillhan/document_src_functions
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

> pull latest `master` branch

```
$ git checkout master
Switched to branch 'master'

$ git pull origin master
remote: Counting objects: 1, done.
remote: Total 1 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (1/1), done.
From https://github.com/pwillhan/git_lesson
 * branch            master     -> FETCH_HEAD
   eca1130..1f2519e  master     -> origin/master
Updating eca1130..1f2519e
Fast-forward
 docs/README.md | 4 ++++
 1 file changed, 4 insertions(+)
 create mode 100644 docs/README.md
 
$ gla
*   1f2519e (HEAD -> master, origin/master) Merge pull request #3 from pwillhan/development
|\  
| *   1aaae8d (origin/development, development) Merge pull request #2 from pwillhan/feature/document_docs
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

> bring `development` branch to the same level as `master` branch

`git rebase master`

```
$ git checkout development
Switched to branch 'development'

$ git rebase master
First, rewinding head to replay your work on top of it...
Fast-forwarded development to master.

$ gla
*   1f2519e (HEAD -> development, origin/master, master) Merge pull request #3 from pwillhan/development
|\  
| *   1aaae8d (origin/development) Merge pull request #2 from pwillhan/feature/document_docs
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

$ git push origin development
Total 0 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      development -> development
```
