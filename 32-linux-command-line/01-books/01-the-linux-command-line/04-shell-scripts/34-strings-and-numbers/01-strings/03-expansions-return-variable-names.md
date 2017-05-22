# Expansions That Return Variable Names

The shell has the ability to return the names of variables.

```
${!prefix*}
${!prefix@}
```

This expansion returns the names of existing variables with names beginning with prefix.

> List variables in the environment with names begin with `BASH`

```
$ echo ${!BASH*}
BASH BASHOPTS BASHPID BASH_ALIASES BASH_ARGC BASH_ARGV BASH_CMDS BASH_COMMAND BASH_COMPLETION_COMPAT_DIR BASH_LINENO BASH_SOURCE BASH_SUBSHELL BASH_VERSINFO BASH_VERSION
```
