# Controlling Processes

> Interrupting a Process

`ctrl+c`

> Putting a Process in the Background

```
﻿$ xlogo &
[1] 23351

﻿$ ps
  PID TTY          TIME CMD
23135 pts/7    00:00:00 bash
23351 pts/7    00:00:00 xlogo
23352 pts/7    00:00:00 ps
```

list jobs launched from the terminal

```
﻿$ jobs
[1]+  Running                 xlogo &
```

> Returning a Process to the Foreground

```
﻿$ jobs
[1]+  Running                 xlogo &
$ fg %1
xlogo
```

> Stopping (Pausing) a Process

`ctrl+z`

```
﻿$ xlogo
^Z
[1]+  Stopped                 xlogo
```

> `bg` move the program to background

```
﻿$ bg %1
[1]+ xlogo &
```

## Common Signals

| Number | Name | Meaning  |
|--------|------|--------------------------------------------------------|
| 1      | HUP  | Hang up. Remove computers with phone lines and modems  |
| 2      | INT  | Interrupt. Same as ctrl+c  |
| 9      | KILL | Kill. A process has no opportunity to "clean up" (save its work)  |
| 15     | TERM | Terminate (default). A program will terminate if it is still "alive" enough to receive signals |
| 18     | CONT | Continue. Will restore a process after a STOP signal |
| 19     | STOP | Stop. Pause a process without terminating. Like KILL signla, it is not sent to process, so it cannot be ignored |

> Examples

```
﻿$ kill -1 3000
william@william-VirtualBox:~$ ps
  PID TTY          TIME CMD
 2368 pts/17   00:00:00 bash
 3006 pts/17   00:00:00 ps
[1]+  Hangup                  xlogo
```

