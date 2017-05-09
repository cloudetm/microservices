# First Stage: Minimal Document

> create a new file name `~/bin/sys_info_page`

```
$ vi ~/bin/sys_info_page

$ cat ~/bin/sys_info_page 
#!/bin/bash

# Program to output a system information page

echo "<HTML>"
echo "    <HEAD>"
echo "        <TITLE>Page Title</TITLE>"
echo "    </HEAD>"
echo "    <BODY>"
echo "        Page body."
echo "    </BODY>"
echo "</HTML>"
```

> make `~/bin/sys_info_page` executable and run it

```
$ chmod 755 ~/bin/sys_info_page 

$ sys_info_page 
<HTML>
    <HEAD>
        <TITLE>Page Title</TITLE>
    </HEAD>
    <BODY>
        Page body.
    </BODY>
</HTML>
```
