# Reading Files with Loops

https://www.safaribooksonline.com/library/view/the-linux-command/9781593273897/ch29s05.html

`while` and `until` can process standard input.
It allows files to be processed with `while` and `until` loops.

## Display contents of distros.txt file

> distros.txt

```
SUSE	10.2	12/07/2006
Fedora	10	11/25/2008
SUSE	11.0	06/18/2008	
Ubuntu	8.04	04/24/2008
Fedora	8	11/08/2007
SUSE	10.3	10/04/2007
Ubuntu	6.10	10/26/2006
Fedora	7	05/31/2007
Ubuntu	7.10	10/18/2007
Ubuntu	7.04	04/19/2007
SUSE	10.1	05/11/2006
Fedora	6	10/24/2006
Fedora	9	5/13/2008
Ubuntu	6.06	06/01/2006
Ubuntu	8.10	10/30/2008
Fedora	5	03/20/2006
```

## To redirect a file to the loop

place the redirection operator after the done statement.

> foo.sh

```
#!/bin/bash

# while-read: read lines from a file

while read distro version release; do
  printf "Distro: %s\tVersion: %s\tRelease: %s\n" $distro $version $release
done < distros.txt
```

> Test

```
$ bash foo.sh 
Distro: SUSE	Version: 10.2	Release: 12/07/2006
Distro: Fedora	Version: 10	Release: 11/25/2008
Distro: SUSE	Version: 11.0	Release: 06/18/2008
Distro: Ubuntu	Version: 8.04	Release: 04/24/2008
Distro: Fedora	Version: 8	Release: 11/08/2007
Distro: SUSE	Version: 10.3	Release: 10/04/2007
Distro: Ubuntu	Version: 6.10	Release: 10/26/2006
Distro: Fedora	Version: 7	Release: 05/31/2007
Distro: Ubuntu	Version: 7.10	Release: 10/18/2007
Distro: Ubuntu	Version: 7.04	Release: 04/19/2007
Distro: SUSE	Version: 10.1	Release: 05/11/2006
Distro: Fedora	Version: 6	Release: 10/24/2006
Distro: Fedora	Version: 9	Release: 5/13/2008
Distro: Ubuntu	Version: 6.06	Release: 06/01/2006
Distro: Ubuntu	Version: 8.10	Release: 10/30/2008
Distro: Fedora	Version: 5	Release: 03/20/2006
Distro: 	Version: 	Release: 
```

## pipe standard input into a loop

> foo.sh

```
#!/bin/bash

# while-read2: read lines from a file

sort -k 1,1 -k 2n distros.txt | while read distro version release; do
  printf "Distro: %s\tVersion: %s\tReleased: %s\n" $distro $version $release
done
```

> Test

```
Distro: 	Version: 	Released: 
Distro: Fedora	Version: 5	Released: 03/20/2006
Distro: Fedora	Version: 6	Released: 10/24/2006
Distro: Fedora	Version: 7	Released: 05/31/2007
Distro: Fedora	Version: 8	Released: 11/08/2007
Distro: Fedora	Version: 9	Released: 5/13/2008
Distro: Fedora	Version: 10	Released: 11/25/2008
Distro: SUSE	Version: 10.1	Released: 05/11/2006
Distro: SUSE	Version: 10.2	Released: 12/07/2006
Distro: SUSE	Version: 10.3	Released: 10/04/2007
Distro: SUSE	Version: 11.0	Released: 06/18/2008
Distro: Ubuntu	Version: 6.06	Released: 06/01/2006
Distro: Ubuntu	Version: 6.10	Released: 10/26/2006
Distro: Ubuntu	Version: 7.04	Released: 04/19/2007
Distro: Ubuntu	Version: 7.10	Released: 10/18/2007
Distro: Ubuntu	Version: 8.04	Released: 04/24/2008
Distro: Ubuntu	Version: 8.10	Released: 10/30/2008
```
