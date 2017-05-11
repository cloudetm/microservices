# Writing First Script

## Executable Permissions

### Octal mode - permission modes

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


> hello_world.sh

```
#!/usr/bin/env bash

# This is our first script

echo 'Hello World!'
```

```
$ ls -l hello_world.sh 
-rw-r--r--  1 whan  386085923  69 May  6 21:17 hello_world.sh
$ chmod 755 hello_world.sh 
$ ls -l hello_world.sh 
-rwxr-xr-x  1 whan  386085923  69 May  6 21:17 hello_world.sh
```

## Script File Location

> explicit path

```
$ ./hello_world.sh 
Hello World!
```

The system searches a list of directories each time it needs to find an executable program, if no explicit path is specified. 
This is how the system knows to execute /bin/ls when we type ls at the command line. 
The list of directories is held within an environment variable named `PATH`.

```
$ echo $PATH
/usr/local/bin:/usr/bin:/usr/local/sbin:/usr/sbin:/home/vagrant/.local/bin:/home/vagrant/bin
```

- Most linux distributions configure the `PATH` variable to contain a `bin` directory in the user's home directory to
  allow users to execute their own programs. So place our script within a bin directory will work.

```
$ mkdir bin
[vagrant@localhost ~]$ mv hello_world.sh bin
[vagrant@localhost ~]$ hello_world.sh 
Hello World!
```

- If the `PATH` variable does not contain the directory, we can easily add it by including this line in `.bashrc` file

```
export PATH=~/bin:"$PATH"
```

reread the `.bashrc` after the change is made. `.` command is a synonym for the source command.

```
. .bashrc
```

## Good Locations for Scripts

- `~/bin` - personal use
- `/usr/local/bin` - everyone
- `/usr/local/sbin` - system administrator

## Long Option Names

> Both short and long option names

```
$ ls -ad # short option name
.

$ ls --all --directory # long option name
.
```

- `short options` are preferred when entering options on the command line to reduce typing
- `long options` can improve readability when writing scripts

## Indentation and Line Continuation

> Example

```
$ find playground \
> -type f -not -perm 0600 -exec chmod 0600 '{}' ';' \
> -or \
> -type d -not -perm 0700 -exec chmod 0700 '{}' ';' \
> 
```
