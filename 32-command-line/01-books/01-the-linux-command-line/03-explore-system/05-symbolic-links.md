# Symbolic Links

aka soft link. It is like windows shortcut.

> Scenario

*Program*

```
A programs requires the use of a shared resource in a file named foo, but foo has frequent version changes.
It is good to include version number so the administrator could what version of foo is installed.
If we change the name of the shared resource, we have to update every program that use it.
```

*Solution - symbolic links*

```
For example, we install version 2.6 of foo, which has the filename foo-2.6.
Create a symbolic link simply called foo (like windows shortcut) that points to foo-2.6.
It means that a program when open file foo, actually open the file foo-2.6.
The programs that rely on foo can find it, and we can still see what actual version is installed.
When upgrade to foo-2.7, add the file to our system, delete the symbolic link foo, 
and create a new one (foo) that pints to the new version.
```
