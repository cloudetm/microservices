# Processes

> Commands

- ps - Report a snapshot of current processes
- top - Display tasks
- jobs - List active jobs
- bg - Place a job in the background
- fg - Place a job in the foreground
- kill - Send a signal to a process
- killall - Kill processes by name
- shutdown - Shut down or reboot the system

## Viewing Processes with ps

| Command | Description  |
|---------|--------------|
| ps      | List current processes  |
| pstree  | Outputs a process list arranged in a tree-like pattern showing parent/child relationship  |
| vmstat  | Outputs a snapshot of system resource usage including memory, swap, and disk I/O |
| xload   | A GUI that draws a graph showing system load over time |
| tload   | draws the graph in the terminal |

> `ps` - list current processes

```
﻿$ ps
  PID TTY          TIME CMD
23135 pts/7    00:00:00 bash
23163 pts/7    00:00:00 ps
```

> `x` option shows all processes

```
﻿$ ps x
  PID TTY      STAT   TIME COMMAND
 1284 ?        Ss     0:00 /lib/systemd/systemd --user
 1285 ?        S      0:00 (sd-pam)
 1288 ?        Ss     0:00 /sbin/upstart --user
 1301 ?        S      0:00 /usr/bin/VBoxClient --clipboard
 1303 ?        Sl     0:00 /usr/bin/VBoxClient --clipboard
 1311 ?        S      0:00 /usr/bin/VBoxClient --display
 1313 ?        S      0:00 /usr/bin/VBoxClient --display
 1316 ?        S      0:00 /usr/bin/VBoxClient --seamless
 1318 ?        Sl     0:00 /usr/bin/VBoxClient --seamless
 1324 ?        S      0:00 /usr/bin/VBoxClient --draganddrop
 1326 ?        Sl     3:39 /usr/bin/VBoxClient --draganddrop
 1430 ?        S      0:00 upstart-udev-bridge --daemon --user
```

> Process States

| State | Meaning                                          |
|-------|--------------------------------------------------|
| R     | Running. The process is running or ready to run  |
| S     | Sleeping. The process is not running; waiting for an event such as a keystroke or network packet  |
| D     | Uninterruptible sleep. The process is waiting for I/O such as a disk drive  |
| T     | Stopped. Process has been instructed to stop |
| Z     | A defunct or "zombie" process. A child process that has terminated but not cleaned up by its parent |
| <     | A high-priority process. Grant more importance to a process, giving it more time on the CPU |
| N     | A low-priority process. Will get processor time only after other higher priority processes are serviced |

> `au` option displays the processes belonging to every user

```
﻿$ ps aux
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0 119700  5876 ?        Ss   Apr27   0:01 /sbin/init spla
root         2  0.0  0.0      0     0 ?        S    Apr27   0:00 [kthreadd]
root         3  0.0  0.0      0     0 ?        S    Apr27   0:00 [ksoftirqd/0]
root         5  0.0  0.0      0     0 ?        S<   Apr27   0:00 [kworker/0:0H]
root         7  0.0  0.0      0     0 ?        S    Apr27   0:04 [rcu_sched]
root         8  0.0  0.0      0     0 ?        S    Apr27   0:00 [rcu_bh]
root         9  0.0  0.0      0     0 ?        S    Apr27   0:00 [migration/0]
root        10  0.0  0.0      0     0 ?        S<   Apr27   0:00 [lru-add-drain]
root        11  0.0  0.0      0     0 ?        S    Apr27   0:00 [watchdog/0]
```

> `top`

displays a continuously updating system process listed in order of process activity

```
﻿$ top
```
