# Searching for Files

> Tools

- locate - Find files by name
- find - Search for files in a directory hierarchy

> Command

- xargs - Build and execute command lines from standard input

> assistance

- touch - Change file times
- stat - Display file or filesystem status

## locate - Find Files the Easy Way

The locate program performs a rapid database search of pathnames and then outputs every name that matches a given substring.

> Install `locate`

```
yum -y update
yum -y install mlocate
updatedb
```

> Search programs begin with `zip`, directories end with `bin/`

```
$ locate bin/zip
/usr/bin/zip
/usr/bin/zipcloak
/usr/bin/zipgrep
/usr/bin/zipinfo
/usr/bin/zipnote
/usr/bin/zipsplit
```

> Combined with other tools such as grep

```
$ locate zip | grep bin
/usr/bin/bunzip2
/usr/bin/bzip2
/usr/bin/bzip2recover
/usr/bin/gpg-zip
/usr/bin/gunzip
/usr/bin/gzip
```
