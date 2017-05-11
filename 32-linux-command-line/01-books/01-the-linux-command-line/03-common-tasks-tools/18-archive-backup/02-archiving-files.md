# Archiving Files

Archiving is the process of gathering up many files and bundling them into a single large file.

## tar - Tape Archiving Utility

> file extension

`tar` program is the classic tool for archiving files

- `.tar`
- `.tgz`

```
tar mode[options] pathname...
```

> `.tar` Modes

| Mode | Description |
|------|-------------|
| c    | Create an archive from a list of files and/or directories |
| x    | Extract an archive |
| r    | Append specified pathnames to the end of an archive |
| t    | List the contents of an archive |

### Example - centos

> Create playground

```
# mkdir -p playground/dir-{00{1..9},0{10..99},100}
# touch playground/dir-{00{1..9},0{10..99},100}/file-{A..Z}
```

> Create a tar archive of the entire playground

```
# tar cf playground.tar playground
```

> List archive contents

```
# tar tf playground.tar
```

> Extract tar archive

```
# mkdir foo
# cd foo
foo]# tar xf ../playground.tar 
foo]# ls
playground
```

## zip - Package and Compress Files

The `zip` problem is both a compression tool and an archiver.

The file format is familiar to Windows users

```
zip options zipfile file...
```

> Installation

```
# yum install zip unzip -y
```

> zip files

```
# zip -r playground.zip playground
# ls playground.zip 
playground.zip
```

> Extract a zip file

```
# cd foo
foo]# unzip ../playground.zip
```
