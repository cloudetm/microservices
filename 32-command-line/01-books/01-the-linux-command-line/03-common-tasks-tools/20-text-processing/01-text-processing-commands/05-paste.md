# paste - Merge Lines of Files

> sort by date

```
$ sort -k 3.7nbr -k 3.1nbr -k 3.4nbr distros.txt > distros-by-date.txt
$ cat distros-by-date.txt 
Dick	20	11/25/2008
Harry	30	06/19/2008
Tom	10	12/07/2006
```

> extract the first two fields

```
$ cut -f 1,2 distros-by-date.txt > distros-versions.txt
$ cat distros-versions.txt 
Dick	20
Harry	30
Tom	10
```

> extract the date

```
$ cut -f 3 distros-by-date.txt > distros-dates.txt
$ cat distros-dates.txt 
11/25/2008
06/19/2008
12/07/2006
```

> use paste to put the dates column ahead of the names and versions

```
$ paste distros-dates.txt distros-versions.txt 
11/25/2008	Dick	20
06/19/2008	Harry	30
12/07/2006	Tom	10
```
