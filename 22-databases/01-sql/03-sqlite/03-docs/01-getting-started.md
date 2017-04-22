# Getting Started

## database and table

> Create database and table

```
$ sqlite3 ex1
SQLite version 3.16.0 2016-11-04 19:09:39
Enter ".help" for usage hints.
sqlite> create table tbl1(one varchar(10), two smallint);
sqlite> insert into tbl1 values('hello!', 10);
sqlite> insert into tbl1 values('bye', 20);
sqlite> select * from tbl1;
hello!|10
bye|20
sqlite> ^D
```

> Open existing database

```
$ ls
ex1
$ sqlite3 ex1
SQLite version 3.16.0 2016-11-04 19:09:39
Enter ".help" for usage hints.
sqlite> select * from tbl1;
hello!|10
bye|20
```

> semicolon - at the end of SQL command

```
sqlite> CREATE TABLE tbl2 (
   ...>   f1 varchar(30) primary key,
   ...>   f2 text,
   ...>   f3 real
   ...> );
sqlite> insert into tbl2 values('1', 'Tom', 10);
sqlite> insert into tbl2 values('2', 'Dick', 20);
sqlite> select * from tbl2;
1|Tom|10.0
2|Dick|20.0
```

> Show current tables

```
sqlite> .tables
tbl1  tbl2
```
