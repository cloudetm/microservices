# Forks a repository and make a change as a collaborator

> github owner: delete collaborator from the owner github

1. Go to https://github.com/pwillhan/git_lesson/settings/collaboration
2. Click `Collaborators`
3. Remove gwillhan as a collaborator by clicking `X` next to `gwillhan`

> github collaborator: Fork repository

1. Go to https://github.com/pwillhan/git_lesson
2. Click `Fork` button

> collaborator: git clone repository

```
$ git clone https://github.com/gwillhan/git_lesson.git
Cloning into 'git_lesson'...
remote: Counting objects: 83, done.
remote: Compressing objects: 100% (49/49), done.
remote: Total 83 (delta 14), reused 83 (delta 14), pack-reused 0
Unpacking objects: 100% (83/83), done.
```

> github collaborator: simulate other collaborator making changes

1. Go to https://github.com/gwillhan/git_lesson/new/master/collaboration
2. Click `Create new file`
3. File name: `collaborator.txt`, content: `i am a collaborator`
4. Click `Commit new file`

> github collaborator: pull request from fork to main repository

1. Go to https://github.com/gwillhan/git_lesson/pulls
2. Click `New pull request`
3. Click `Create pull request`, add comment, click `Create pull request`

> github owner: merge changes from fork repository

1. Go to https://github.com/pwillhan/git_lesson/pulls
2. Click `Create collaborator.txt` pull request
3. Click `Merge pull request`, click `Confirm merge`
