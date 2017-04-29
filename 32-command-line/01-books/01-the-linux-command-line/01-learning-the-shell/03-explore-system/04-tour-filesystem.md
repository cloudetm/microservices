# A Guided Tour filesystem

> Tour begin

```
﻿$ cd

$ ls -l
total 48
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Desktop
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Documents
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Downloads
-rw-r--r-- 1 william william 8980 Apr 14 17:17 examples.desktop
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Music
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Pictures
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Public
drwxrwxr-x 2 william william 4096 Apr 28 10:12 temp
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Templates
drwxr-xr-x 2 william william 4096 Apr 14 17:22 Videos
```

## Linux systems directories

> `/`

The root directory, where everything begins.

> `/bin`

Contains binaries (programs) that must be present for the system to boot and run.

> `/boot`

Contains the Linux kernel, initial RAM disk image(for drivers needed at boot time), and the boot loader.

> `dev`

Special directory that contains device nodes. Kernal maintains a list of all the devices it understands.

> `/etc`

Contains all of the system-wide configuration files and shell scripts that start each of the system services at boot time. 

> `/home`

Each user is given a directory in /home. Ordinary users can write files only in their home directories.

> `/lib`

Contains shared library files used by the core system programs. There are similar to DLLs in Windows.

> `/media`

Contains the mount points for removable media such as USB drives, CD-ROMs, etc.

> `opt`

Install "optional" software. Hold commercial software products that may be installed on the system.

> `/proc`

Virtual filesystem maintained by the Linux kernel. The "files" it contains are peepholes into the kernel itself.

> `/root`

home directory for the root account.

> `sbin`

Contains "system" binaries that perform vital system tasks that are generally reserved for the superuse.

> `/tmp`

Temporary storage, transient files created by various programs.

> `/usr`

Contains all the programs and support files used by regular users.

> `/usr/bin`

Contains the executable programs installed by the Linux distribution.

> `/usr/lib`

The shared libraries for the programs in /usr/bin.

> `/usr/local`

Programs are not included with your distribution but are intended for system-wide use are installed.

> `/usr/sbin`

Contains system administration programs.

> `/usr/share`

Contains shared data used by programs in /usr/bin.

> `/usr/share/doc`

Documentation files organized by package.

> `/var`

Data that is likely to change is stored. Various databases, spool files, user mail, etc.

> `/var/log`

Contains log files records of various system activity. These are very important and should be monitored.
