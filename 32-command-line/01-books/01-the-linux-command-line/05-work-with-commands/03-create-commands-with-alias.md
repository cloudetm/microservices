# Creating Your Own Commands with alias

> multiple commands on a line

```
command1; command2; command3...
```

*Example*

```
﻿$ cd /usr; ls;
bin  games  include  lib  local  locale  sbin  share  src
```

> create alias

```
$ alias foo='cd /usr; ls'
/usr$ foo
bin  games  include  lib  local  locale  sbin  share  src

/usr$ type foo
foo is aliased to `cd /usr; ls'
```

> `unalias`

```
$ unalias foo
william@william-VirtualBox:/usr$ type foo
bash: type: foo: not found
```
