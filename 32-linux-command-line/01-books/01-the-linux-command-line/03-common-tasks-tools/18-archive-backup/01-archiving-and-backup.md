# Archiving and Backup

> Compression programs

- gzip - Compress or expand files
- bzip2 - A block sorting file compressor

> Archiving programs

- tar - Tape-archiving utility
- zip - Package and compress files

> synchronization program

- rsync - Remote file and directory synchronization

## Compressing Files

> gzip - Compress or Expand Files

The `gzip` program is used to compress one or more files. 
The `gunzip` program is used to restore compressed files to original

```
$ ls -l /etc > foo.txt
$ ls
foo.txt
$ gzip foo.txt 
$ ls
foo.txt.gz
$ gunzip foo.txt.gz 
$ ls
foo.txt
```

> gzip Options

| Option | Description  |
|--------|----------------------------|
| -c     | Write output to standard output and keep original files |
| -d     | Decompress. Act like gunzip |
| -f     | Force compression even if a compressed version of the original file already exists |
| -h     | Display usage information |
| -v     | Display verbose messages while compressing |

*-v*

```
gzip foo.txt -v
foo.txt:	 78.6% -- replaced with foo.txt.gz
```
