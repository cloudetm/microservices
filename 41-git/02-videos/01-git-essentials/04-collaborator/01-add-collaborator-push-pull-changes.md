# Add a collaborator and push and pull down changes

> add Collaborator

1. Go to https://github.com/pwillhan/git_lesson/settings/collaboration
2. Enter `gwillhan`, click `Add collaborator`
3. Login `gwillhan` email and Accept invitation

> add collaboration README.md

```
$ mkdir collaboration

$ nano collaboration/README.md
$ cat collaboration/README.md 
files and folder used for collaboration

$ git add .
$ git commit -m 'readme and folder for collaboration'
[master 1617077] readme and folder for collaboration
 1 file changed, 1 insertion(+)
 create mode 100644 collaboration/README.md
 
$ git push origin master
Counting objects: 4, done.
Delta compression using up to 8 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (4/4), 465 bytes | 0 bytes/s, done.
Total 4 (delta 0), reused 0 (delta 0)
To https://github.com/pwillhan/git_lesson.git
   6eca3f6..1617077  master -> master
```

> github

Verify the both owner and collaborator github have the `collaboration/README.md`
