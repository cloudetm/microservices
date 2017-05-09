# diff - Compare Files Line by Line

> compare two files

```
$ diff file1.txt file2.txt 
1d0
< a
4a4
> e
```

## `-c` option - context format

```
$ diff -c file1.txt file2.txt 
*** file1.txt	2017-05-08 20:33:38.082575143 +0000
--- file2.txt	2017-05-08 20:33:55.282023586 +0000
***************
*** 1,4 ****
- a
  b
  c
  d
--- 1,4 ----
  b
  c
  d
+ e
```

> diff Context-Format Change Indicators

| Indicator | Meaning     |
|-----------|-------------|
| (none)    | A line shown for context. It does not indicate a difference between the two files |
| -         | A line deleted. This line will appear in the first file but not in the second file |
| +         | A line added. This line will appear in the second file but not in the first file |
| !         | A line changed. The two versions of the line will be displayed, each in its respective section of the change group |

## `-u` option - unified format

```
$ diff -u file1.txt file2.txt 
--- file1.txt	2017-05-08 20:33:38.082575143 +0000
+++ file2.txt	2017-05-08 20:33:55.282023586 +0000
@@ -1,4 +1,4 @@
-a
 b
 c
 d
+e
```

> diff Unified-Format Change Indicators

| Character | Meaning      |
|-----------|--------------|
| (none)    | This line is shared by both files |
| -         | This line was removed from the first file |
| +         | This line was added to the first file |
