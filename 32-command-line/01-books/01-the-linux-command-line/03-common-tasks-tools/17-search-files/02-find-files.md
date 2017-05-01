# find—Find Files the Hard Way

## `find`

> list of home directory

```
$ find ~
/root
/root/.bash_logout
/root/.bash_profile
/root/.bashrc
/root/.cshrc
/root/.tcshrc
/root/original-ks.cfg
/root/anaconda-ks.cfg
/root/.pki
/root/.pki/nssdb
/root/.bash_history
```

> count number of files and directories

```
$ find ~ | wc -l
11
```

> count number of directories

```
$ find ~ -type d | wc -l
3
```

> count number of files

```
$ find ~ -type f | wc -l
8
```

> find File Types

| File Type | Description |
|-----------|-------------|
| b         | Block special device file |
| c         | Character special device file |
| d         | Directory  |
| f         | Regular file |
| l         | Symbolic link |

> find files match wildcard pattern *.JPG and bigger than 1 megabyte

```
$ find ~ -type f -name "*.JPG" -size +1M | wc -l
0
```

MORE

