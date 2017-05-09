# Variables and Constants

> create a variable named title

```
$ cat ~/bin/sys_info_page 
#!/bin/bash

# Program to output a system information page

title="System Information Report"

echo "<HTML>
    <HEAD>
        <TITLE>$title</TITLE>
    </HEAD>
    <BODY>
        <H1>$title</H1>
    </BODY>
</HTML>"

$ sys_info_page 
<HTML>
    <HEAD>
        <TITLE>System Information Report</TITLE>
    </HEAD>
    <BODY>
        <H1>System Information Report</H1>
    </BODY>
</HTML>
```

## variables and constants

- Constants - uppercase letters
- Variables - lowercase letters

> Constant `TITLE` example

```
$ cat ~/bin/sys_info_page
#!/bin/bash

# Program to output a system information page

TITLE="System Information Report for $HOSTNAME"

echo "<HTML>
    <HEAD>
        <TITLE>$TITLE</TITLE>
    </HEAD>
    <BODY>
        <H1>$TITLE</H1>
    </BODY>
</HTML>"

$ sys_info_page 
<HTML>
    <HEAD>
        <TITLE>System Information Report for localhost.localdomain</TITLE>
    </HEAD>
    <BODY>
        <H1>System Information Report for localhost.localdomain</H1>
    </BODY>
</HTML>
```