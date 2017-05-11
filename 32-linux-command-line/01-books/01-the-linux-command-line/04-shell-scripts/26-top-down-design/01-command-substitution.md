# Top-Down Design

> shell functions

```
function name {
        commands
        return
}
```

equivalent

```
name () {
        commands
        return
}
```

> command substitution

- `$(report_uptime)` - `report_uptime()` function
- `$(report_disk_space)` - report_disk_space() function
- `$(report_home_space)` - report_home_space() function

```
$ cat ~/bin/sys_info_page
#!/bin/bash

# Program to output a system information page

TITLE="System Information Report for $HOSTNAME"

report_uptime() {
    echo 'uptime'
    return
}

report_disk_space() {
    echo 'disk_space'
    return
}

report_home_space() {
    echo 'home_space'
    return
}

cat << _EOF_
<HTML>
    <HEAD>
        <TITLE>$TITLE</TITLE>
    </HEAD>
    <BODY>
        <H1>$TITLE</H1>
        $(report_uptime)
        $(report_disk_space)
        $(report_home_space)
    </BODY>
</HTML>
_EOF_

$ sys_info_page 
<HTML>
    <HEAD>
        <TITLE>System Information Report for localhost.localdomain</TITLE>
    </HEAD>
    <BODY>
        <H1>System Information Report for localhost.localdomain</H1>
        uptime
        disk_space
        home_space
    </BODY>
</HTML>
```
