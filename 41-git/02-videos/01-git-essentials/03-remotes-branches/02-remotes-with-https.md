# Use remotes with https

> github.com

1. Go to https://github.com/
2. Click `New repository`, Repository name: `git_lesson`, click `Create repository`
3. On Quick setup, select `HTTPS`
 
> terminal

```
$ git remote add origin https://github.com/pwillhan/git_lesson.git

$ git remote -v
origin	https://github.com/pwillhan/git_lesson.git (fetch)
origin	https://github.com/pwillhan/git_lesson.git (push)

$ git push origin master
Counting objects: 16, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (11/11), done.
Writing objects: 100% (16/16), 1.44 KiB | 0 bytes/s, done.
Total 16 (delta 5), reused 0 (delta 0)
remote: Resolving deltas: 100% (5/5), done.
To https://github.com/pwillhan/git_lesson.git
 * [new branch]      master -> master
```
