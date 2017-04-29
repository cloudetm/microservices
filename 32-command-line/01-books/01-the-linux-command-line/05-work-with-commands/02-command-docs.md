# Getting a Command’s Documentation

> help - Get Help for Shell Builtins

bash has a built-in help facility for each of the shell builtins

```
$ help cd
﻿cd: cd [-L|[-P [-e]] [-@]] [dir]
    Change the shell working directory.
    
    Change the current directory to DIR.  The default DIR is the value of the
    HOME shell variable.
    
    The variable CDPATH defines the search path for the directory containing
    DIR.  Alternative directory names in CDPATH are separated by a colon (:).
    A null directory name is the same as the current directory.  If DIR begins
    with a slash (/), then CDPATH is not used.
    
    If the directory is not found, and the shell option `cdable_vars' is set,
    the word is assumed to be  a variable name.  If that variable has a value,
    its value is used for DIR.
    
    Options:
        -L	force symbolic links to be followed: resolve symbolic links in
    	DIR after processing instances of `..'
        -P	use the physical directory structure without following symbolic
    	links: resolve symbolic links in DIR before processing instances
    	of `..'
        -e	if the -P option is supplied, and the current working directory
    	cannot be determined successfully, exit with a non-zero status
        -@  on systems that support it, present a file with extended attributes
            as a directory containing the file attributes
    
    The default is to follow symbolic links, as if `-L' were specified.
    `..' is processed by removing the immediately previous pathname component
    back to a slash or the beginning of DIR.
    
    Exit Status:
    Returns 0 if the directory is changed, and if $PWD is set successfully when
    -P is used; non-zero otherwise.
```

> --help - Display Usage Information

displays a description of the command’s supported syntax and options

```
$ mkdir --help
Usage: mkdir [OPTION]... DIRECTORY...
Create the DIRECTORY(ies), if they do not already exist.

Mandatory arguments to long options are mandatory for short options too.
  -m, --mode=MODE   set file mode (as in chmod), not a=rwx - umask
  -p, --parents     no error if existing, make parent directories as needed
  -v, --verbose     print a message for each created directory
  -Z                   set SELinux security context of each created directory
                         to the default type
      --context[=CTX]  like -Z, or if CTX is specified then set the SELinux
                         or SMACK security context to CTX
      --help     display this help and exit
      --version  output version information and exit

GNU coreutils online help: <http://www.gnu.org/software/coreutils/>
Full documentation at: <http://www.gnu.org/software/coreutils/mkdir>
or available locally via: info '(coreutils) mkdir invocation'
```

> man - Display a Program's Manual Page

It is possible to search the list of man pages for possible matches based on a search term.

```
$ man ls
```

> apropos - Display Appropriate Commands

```
$ apropos floppy
fdformat (8)         - low-level format a floppy disk
mbadblocks (1)       - tests a floppy disk, and marks the bad blocks in the FAT
mformat (1)          - add an MSDOS filesystem to a low-level formatted flopp...
mxtar (1)            - Wrapper for using GNU tar directly from a floppy disk
```

> whatis - Display a Very Brief Description of a Command

```
$ whatis ls
ls (1)               - list directory contents
```

> info - Display a Program's Info Entry

Info pages are displayed with a reader program named, appropriately enough, info.

```
$ info coreutils
```
