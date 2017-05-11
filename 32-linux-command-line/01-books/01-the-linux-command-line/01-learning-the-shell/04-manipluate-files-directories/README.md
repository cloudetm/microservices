# Manipulating Files and Directories

https://www.safaribooksonline.com/library/view/the-linux-command/9781593273897/ch04.html

## Frequently used Linux commands

> `cp`

Copy files and directories

> `mv`

Move/rename files and directories

> `mkdir`

Create directories

> `rm`

Remove files and directories

> `ln`

Create hard and symbolic links

> `cp -u *.html destination`

copy all the HTML file from one directory to another 
but only those that not exist in the destination directory or are newer than the versions in the destination directory

> Wildcards

| Wildcard      | Matches                                         |
|---------------|-------------------------------------------------|
| *             | Any characters                                  |
| ?             | Any single character                            |
| [characters]  | Any character is a member of set characters     |
| [!characters] | Any character is not a member of set characters |
| [:class:]     | Any character is a member of specified class    |

> Commonly used character classes

| Character Class | Matches                    |
|-----------------|----------------------------|
| [:alnum:]       | Any alphanumeric character |
| [:alpha:]       | Any alphabetic character   |
| [:digit:]       | Any numeral                |
| [:lower:]       | Any lowercase letter       |
| [:upper:]       | Any uppercase letter       |

> Wildcard Examples

| Pattern                | Matches                                                                   |
|------------------------|---------------------------------------------------------------------------|
| *                      | Any files                                                                 |
| g*                     | Any file beginning with g                                                 |
| b*.txt                 | Any file beginning with b followed by any characters and ending with .txt |
| Data???                | Any file beginning with Data followed by exactly three characters         |
| [abc]*                 | Any file beginning with either a, c, or c                                 |
| BACKUP.[0-9][0-9][0-9] | Any file beginning with BACKUP. followed by exactly three numerals        |
| [[:upper:]]*           | Any file beginning with an uppercase letter                               |
| [![:digit"]]*          | Any file not beginning with numeral                                       |
| *[[:lower:]123]        | Any file ending with a lowercase letter or the numerals 1, 2, or 3        |
