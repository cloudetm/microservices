# Cleanup local git

```
$ git branch -a
  document_src_functions
* master
  my_branch
  remotes/origin/document_src_functions
  remotes/origin/master

$ git fetch --prune
From https://github.com/pwillhan/git_lesson
 - [deleted]         (none)     -> origin/document_src_functions
 
$ git branch -d my_branch
Deleted branch my_branch (was 2d7b5d6).

$ git branch -a
  document_src_functions
* master
  remotes/origin/master
```
