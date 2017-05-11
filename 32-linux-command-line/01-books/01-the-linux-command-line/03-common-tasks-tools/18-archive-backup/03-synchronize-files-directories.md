# Synchronizing Files and Directories

Maintain a backup copy of a system involves keeping one or more directories synchronized 
with another directory located on either the local system or a remote system.
 
For example, a copy of website under development and synchronize with the "live" copy on a remote web server

## rsync - Remote File and Directory Synchronization

`resync` can synchronize both local and remote directories by using the rsync remote-update protocol, which allows 
resync to quickly detect the differences between two directories and perform the minimum amount of copying required.

```
rsync options source destination
```

- A local file or directory
- A remote file or directory in the form of - [user@]host:path
- A remote rsync server specified with a URI of rsync://[user@]host[:port]/path

Note: either the source or the destination must be a local file. Remote-to-remote copying is not supported.

> clean out `foo` directory

```
# rm -rf foo/*
```

> synchronize the playground directory with a corresponding copy in `foo` directory

```
# rsync -av playground foo
```

- `-a` for archiving causes recursion and preservation of file attributes
- `-v` verbose output

```
# rsync -av --delete playground foo
```

- `--delete` option removes files existed on backup but no longer existed on the source device

## Using rsync over a Network

`rsync` can copy files over a network. `r` in rsync stands for remote.

