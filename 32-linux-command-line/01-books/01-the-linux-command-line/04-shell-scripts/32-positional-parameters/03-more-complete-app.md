# A More Complete Application

## Implement the command-line processing

> foo.sh

```
#!/bin/bash

usage () {
  echo "$PROGNAME: usage: $PROGNAME [-f file | -i]"
  return
}

# process command line options

interactive=
filename=

while [[ -n $1 ]]; do # `-n` check flow-control/test-expression
  case $1 in
    -f | --file)        shift # it causes each parameter to move down one each time it is executed
                        filename=$1
                        ;;
    -i | --interactive) interactive=1
                        ;;
    -h | --help)        usage # call usage()
                        exit
                        ;;
    *)                  usage >&2
                        exit 1
                        ;;
  esac
  shift
done

echo "interactive=$interactive"
echo "filename=$filename"
```

> Test

```
$ bash foo.sh -h
: usage:  [-f file | -i]

$ bash foo.sh -f foo1.txt
interactive=
filename=foo1.txt

$ bash foo.sh -i
interactive=1
filename=

$ bash foo.sh -f foo1.txt -i
interactive=1
filename=foo1.txt
```

## interactive mode

> foo.sh

```
#!/bin/bash

usage () {
  echo "$PROGNAME: usage: $PROGNAME [-f file | -i]"
  return
}

# process command line options

interactive=1
filename=

if [[ -n $interactive ]]; then
  while true; do
    read -p "Enter name of output file: " filename
    if [[ -e $filename ]]; then
      read -p "'$filename' exists. Overwrite? [y/n/q] > "
      case $REPLY in
        Y|y)  break
              ;;
        Q|q)  echo "Program terminated."
              exit
              ;;
        *)    continue
              ;;
      esac
    elif [[ -z $filename ]]; then
      continue
    else
      break
    fi
  done
fi     

echo "interactive=$interactive"
echo "filename=$filename"
```

> Test

```
$ bash foo.sh
Enter name of output file: foo1.txt
'foo1.txt' exists. Overwrite? [y/n/q] > y
interactive=1
filename=foo1.txt

$ bash foo.sh
Enter name of output file: foo1.txt
'foo1.txt' exists. Overwrite? [y/n/q] > n
Enter name of output file: foo1.txt
'foo1.txt' exists. Overwrite? [y/n/q] > q
Program terminated.
```

## Output filename

> foo.sh

```
#!/bin/bash

# sys_info_page: program to ouput a system information page

PROGNAME=$(basename $0)
TITLE="System Information Report for $HOSTNAME"
CURRENT_TIME=$(date +"%x %r %Z")
TIME_STAMP="Generated $CURRENT_TIME, by $USER"

report_uptime (){
	cat <<- _EOF_
		<H2> System uptime </H2>
		<PRE>$(uptime)</PRE>
		_EOF_
	return
}

report_disk_space() {
	cat <<- _EOF_
		<H2>Disk Space Ultilization</H2>
		<PRE>$(df -h)</PRE>
		_EOF_
	return
}

report_home_space(){
	if [[ $(id -u) -eq 0 ]]; then
		cat <<- _EOF_
			<H2> Home Space Ultilization (All Users) </H2>
			<PRE> $(du -sh /home/*)</PRE>
			_EOF_
	else
		cat <<- _EOF_
			<H2> Home Space Ultilization ($USER) </H2>
			<PRE> $(du -sh $HOME)</PRE>
			_EOF_
	fi
	return
}

usage(){
	echo "$PROGNAME: usage : $PROGNAME [-f file | -i]"
	return
}

write_html_page() {
	cat<<- _EOF_
	<HTML>
		<HEAD>
			<TITLE>$TITLE</TITLE>
		</HEAD>
		<BODY>
			<H1>$TITLE</H1>
			<P> $TIME_STAMP </P>
			$(report_uptime)
			$(report_disk_space)
			$(report_home_space)
		</BODY>
	_EOF_
	return
}

# process command line options

interactive=1
filename=

while [[ -n $1 ]]; do
	case $1 in 
		-f | --file)		shift
							filename=$1
							;;
		-i | --interactive)	interactive=1
							;;
		-h | --help)		usage
							exit
							;;
		*)					usage
							exit 1
							;;
	esac
	shift
done

if [[ -n $interactive ]]; then
	while true; do
		read -p "Enter the name of the ouput file: " filename
		if [[ -e $filename ]]; then
			read -p "'$filename' exist. Overwrite? [y/n/q] > "
			case $REPLY in
				Y|y)		break
							;;
				Q|q)		echo "Program terminated"
							exit
							;;
				*)			continue
							;;
			esac
		fi
	done
fi

if [[ -n $filename ]]; then
	if touch $filename && [[ -f $filename ]]; then
		write_html_page > $filename
	else
		echo "$PROGNAME: cannot write file '$filename'" >$2
	fi
else
	write_html_page
fi
```

> Test

```
$ bash foo.sh
Enter the name of the ouput file: foo1.txt
'foo1.txt' exist. Overwrite? [y/n/q] > y

$ cat foo1.txt
<HTML>
<HEAD>
<TITLE>System Information Report for localhost.localdomain</TITLE>
</HEAD>
<BODY>
<H1>System Information Report for localhost.localdomain</H1>
<P> Generated 05/21/2017 05:15:06 AM UTC, by vagrant </P>
<H2> System uptime </H2>
<PRE> 05:15:13 up 41 min,  1 user,  load average: 0.00, 0.01, 0.03</PRE>
<H2>Disk Space Ultilization</H2>
<PRE>Filesystem                       Size  Used Avail Use% Mounted on
/dev/mapper/VolGroup00-LogVol00   38G  1.2G   37G   4% /
devtmpfs                         233M     0  233M   0% /dev
tmpfs                            245M     0  245M   0% /dev/shm
tmpfs                            245M  4.4M  241M   2% /run
tmpfs                            245M     0  245M   0% /sys/fs/cgroup
/dev/sda2                       1014M   89M  926M   9% /boot
tmpfs                             49M     0   49M   0% /run/user/1000
tmpfs                             49M     0   49M   0% /run/user/0</PRE>
<H2> Home Space Ultilization (vagrant) </H2>
<PRE> 120K	/home/vagrant</PRE>
</BODY>
```
