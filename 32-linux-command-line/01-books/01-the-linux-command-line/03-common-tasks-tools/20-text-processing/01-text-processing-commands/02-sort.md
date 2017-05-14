# sort - Sort Lines of Text Files

> process standard input directly from the keyboard

```
$ sort > foo.txt
c    
b
a

$ cat foo.txt
a
b
c
```

> Common sort Options

| Option | Long Option             | Description |
|--------|-------------------------|-------------|
| -b     | --ignore-leading-blanks | Sorting based on the first non-whitespace character on the line |
| -f     | --ignore-case           | Make sorting case insensitive |
| -n     | --numeric-sort          | Sorting based on the numeric evaluation of a string |
| -r     | --reverse               | Sort in reverse order. Results are in descending rather than ascending order |
| -k     | --key=field1[,field2]   | Sort based on a key field located from field1 to field2 rather than line |
| -m     | --merge                 | Merge multiple files into a single sorted result without additional sorting |
| -o     | --output=file           | Send sorted output to file rather than to standard output |
| -t     | --field-separator=char  | Define the field-separator character. By default, fields are separated by spaces or tabs |

> list summary in pathname in reverse numerical sort order to show the 10 largest consumers of space
 
```
$ du -s /usr/share/* | sort -nr | head
97196	/usr/share/locale
35352	/usr/share/doc
25364	/usr/share/man
18828	/usr/share/backgrounds
9704	/usr/share/i18n
9192	/usr/share/cracklib
6648	/usr/share/hwdata
4932	/usr/share/zoneinfo
4868	/usr/share/mime
2848	/usr/share/misc
```

> multikey sort

- first option `1,1` means "start at field 1 and end at field 1"
- second option `2n` means field 2 is the sort key and that the sort should be numeric

```
$ sort --key=1,1 --key=2n distros.txt 
Fedora	5	03/20/2006
Fedora	6	10/24/2006
Fedora	7	05/31/2007
Fedora	8	11/08/2007
Fedora	9	5/13/2008
Fedora	10	11/25/2008
SUSE	10.1	05/11/2006
SUSE	10.2	12/07/2006
SUSE	10.3	10/04/2007
SUSE	11.0	06/18/2008	
Ubuntu	6.06	06/01/2006
Ubuntu	6.10	10/26/2006
Ubuntu	7.04	04/19/2007
Ubuntu	7.10	10/18/2007
Ubuntu	8.04	04/24/2008
Ubuntu	8.10	10/30/2008
```
