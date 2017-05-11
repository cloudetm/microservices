# Permissions

Unix is multitasking systems, and also multiuser systems.
Remote users can log in via ssh (secure shell) and operate the computer.

> System security

- id - Display user identify
- chmond - Change a file's mode
- umask - Set the default file permissions
- su - Run a shell as another user
- sudo - Execute a command as another user
- chown - Change a file's owner
- chgrp - Change a file's group ownership
- passwd - Change a user's password

## Owners, Group Members, and Everyboday Else

> regular users do not have permission to read `/etc/shadow`

```
﻿$ file /etc/shadow
/etc/shadow: regular file, no read permission
$ less /etc/shadow
/etc/shadow: Permission denied
```

> find out information about your identity

```
﻿$ id
uid=1000(william) gid=1000(william) groups=1000(william),4(adm),24(cdrom),27(sudo),30(dip),46(plugdev),113(lpadmin),128(sambashare)
```
