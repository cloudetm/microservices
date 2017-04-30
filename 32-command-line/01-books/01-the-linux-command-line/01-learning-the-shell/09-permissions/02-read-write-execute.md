# Reading, Writing, and Executing

**Permission Meanings**

| Permission | File                                                     | Directory                                                           |
|------------|----------------------------------------------------------|---------------------------------------------------------------------|
| r | View the contents of the file. <br>Required for cat, head, less.  | View the contents of the directory. <br>Required for ls.            |
| w | Change the contents of the file. <br>Required for nano, vi.       | Add and remove files in the directory. <br>Required for touch rm.   |
| x | Run the file as a command. <br>Required for binaries and scripts. | Access the contents of the directory. Required for cd, ls -l, find. |


`-rw-r--r--. 1 root root 83 Dec 11 2013 /etc/hosts`: Long Listing Fields for `ls -l newfile`

- first `-`: File Type `-` ordinary file, `d` directory

- next three `rw-`: Owner permissions

- next three `r--`: Group permissions

- next three `r--`: Other permissions

- next special character `.`: Advanced

- next integer `1`: Link Count - hard links

- first `root`: Owner

- second `root`: Group

- `83`: Size

- `Dec 11 2013`: Last Change Date

- `/etc/hosts`: Name

> chmod - Change File Mode

Use `chmod` to change the mode (permissions) of a file or directory

## SYMBOLIC REPRESENTATION

> `chmod` Symbolic Notation

| Symbol | Meaning                                              |
|--------|------------------------------------------------------|
| u      | Short for user but means the file or directory owner |
| g      | Group owner                                          |
| o      | Short for others but means world                     |
| a      | Short for all, the combination of u, g, and o        |

> Examples

| Symbol    | Meaning                                                                                       |
|-----------|-----------------------------------------------------------------------------------------------|
| u+x       | Add execute permission for the owner                                                          |
| u-x       | Remove execute permission from the owner                                                      |
| +x        | Add execute permission for the owner                                                          |
| o-rw      | Remove read and write permission from anyone besides owner and group owner                    |
| go=rw     | Set group owner and anyone besides owner to have read and write permission                    |
| u+x,go=rx | Add execute permission for owner and set permissions for group and others to read and execute |

> Files

```
[osboxes@osboxes lesson6]$ man chmod
[osboxes@osboxes lesson6]$ touch newfile
[osboxes@osboxes lesson6]$ ls -l newfile
-rw-rw-r--. 1 osboxes osboxes 0 Nov 30 03:06 newfile

[osboxes@osboxes lesson6]$ chmod g-w newfile <- remove write permission for group
[osboxes@osboxes lesson6]$ ls -l newfile
-rw-r--r--. 1 osboxes osboxes 0 Nov 30 03:06 newfile

[osboxes@osboxes lesson6]$ chmod o=rw newfile <- add read/write permissions for other users
[osboxes@osboxes lesson6]$ ls -l newfile
-rw-r--rw-. 1 osboxes osboxes 0 Nov 30 03:06 newfile

[osboxes@osboxes lesson6]$ chmod g+w,o-w newfile <- change group and others permissions at once
[osboxes@osboxes lesson6]$ ls -l newfile
-rw-rw-r--. 1 osboxes osboxes 0 Nov 30 03:06 newfile
```

> Directories

**setup**

```
[osboxes@osboxes lesson6]$ mkdir dir_normal
[osboxes@osboxes lesson6]$ touch dir_normal/file1
[osboxes@osboxes lesson6]$ touch dir_normal/file2
[osboxes@osboxes lesson6]$ ls -l dir_normal/
total 0
-rw-rw-r--. 1 osboxes osboxes 0 Nov 30 03:17 file1
-rw-rw-r--. 1 osboxes osboxes 0 Nov 30 03:17 file2
[osboxes@osboxes lesson6]$ ls -ld dir_normal
drwxrwxr-x. 2 osboxes osboxes 30 Nov 30 03:17 dir_normal
```

```
[osboxes@osboxes lesson6]$ chmod g-w dir_normal/ <- remove write permission for group
[osboxes@osboxes lesson6]$ ls -ld dir_normal
drwxr-xr-x. 2 osboxes osboxes 30 Nov 30 03:17 dir_normal

[osboxes@osboxes lesson6]$ chmod -R g-w dir_normal/* <- change all files in a directory
[osboxes@osboxes lesson6]$ ls -l dir_normal/
total 0
-rw-r--r--. 1 osboxes osboxes 0 Nov 30 03:17 file1
-rw-r--r--. 1 osboxes osboxes 0 Nov 30 03:17 file2
```

## Octal mode - permission modes

| Symbol | Binary | Octal | 
|--------|--------|-------|
| ---    | 000    | 0     |
| --x    | 001    | 1     |
| -w-    | 010    | 2     |
| -wx    | 011    | 3     |
| r--    | 100    | 4     |
| r-x    | 101    | 5     |
| rw-    | 110    | 6     |
| rwx    | 111    | 7     |

**Directories**

```
[osboxes@osboxes lesson6]$ mkdir example
[osboxes@osboxes lesson6]$ ls -ld example/
drwxrwxr-x. 2 osboxes osboxes 6 Nov 30 03:32 example/

[osboxes@osboxes lesson6]$ chmod 750 example/ <- owner: rwx, group: r-w, others: ---
[osboxes@osboxes lesson6]$ ls -ld example/
drwxr-x---. 2 osboxes osboxes 6 Nov 30 03:32 example/

[osboxes@osboxes lesson6]$ chmod 770 example/ <- owner: rwx, group: rwxm others: ---
[osboxes@osboxes lesson6]$ ls -ld example/
drwxrwx---. 2 osboxes osboxes 6 Nov 30 03:32 example/

[osboxes@osboxes lesson6]$ chmod 755 example/ <- owner: rwx, group: r-x, others: r-x
[osboxes@osboxes lesson6]$ ls -ld example/
drwxr-xr-x. 2 osboxes osboxes 6 Nov 30 03:32 example/
```

**Files**

```
[osboxes@osboxes lesson6]$ touch newfile2
[osboxes@osboxes lesson6]$ ls -l newfile2
-rw-rw-r--. 1 osboxes osboxes 0 Nov 30 03:38 newfile2
[osboxes@osboxes lesson6]$ chmod 640 newfile2
[osboxes@osboxes lesson6]$ ls -l newfile2
-rw-r-----. 1 osboxes osboxes 0 Nov 30 03:38 newfile2
[osboxes@osboxes lesson6]$ chmod 444 newfile2 <- change it to be read only
[osboxes@osboxes lesson6]$ ls -l newfile2
-r--r--r--. 1 osboxes osboxes 0 Nov 30 03:38 newfile2
```
