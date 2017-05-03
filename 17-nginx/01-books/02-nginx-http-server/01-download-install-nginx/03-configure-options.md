# Configure Options

> configure - ensure the system contains all of the necessary components

```
﻿~/src# cd nginx-1.8.0
﻿~/src/nginx-1.8.0# ./configure
```

> compile the application

```
﻿~/src/nginx-1.8.0# make
﻿~/src/nginx-1.8.0# make install
```

## Path options

When running the configure command, you are offered the possibility to enable some switches that let you specify the
directory or file paths for a variety of elements.

> append some text to the command line

```
./configure --conf-path=/etc/nginx/nginx.conf
```

> a list of the configuration switches for configuring paths

