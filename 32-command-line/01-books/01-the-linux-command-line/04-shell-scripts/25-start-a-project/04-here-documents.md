# Here Documents

A `here document` is an additional form of I/O redirection in which we embed a body of text into our script and feed it
into the standard input of a command.

```
command<< token
text
token
```

> `here document` example

```
$ cat ~/bin/sys_info_page
#!/bin/bash

# Program to output a system information page

TITLE="System Information Report for $HOSTNAME"

cat << _EOF_
<HTML>
    <HEAD>
        <TITLE>$TITLE</TITLE>
    </HEAD>
    <BODY>
        <H1>$TITLE</H1>
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
    </BODY>
</HTML>
```
