# Core Data Types

## Numbers

```
$ python
>>> 123
123
>>> 3.14
3.14
>>> 3+4j
(3+4j)
>>> 0b111
7
```

## Strings

```
>>> 'spam'
'spam'
>>> "Bob's"
"Bob's"
```

> Pattern Matching

```
>>> import re
>>> match = re.match('Hello[ \t]*(.*)world', 'Hello    Python world')
>>> match.group(1)
'Python '
```

## Lists

```
>>> [1, [2, 'three'], 4.5]
[1, [2, 'three'], 4.5]
>>> list(range(10))
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```

## Dictionaries

```
>>> {'food': 'spam', 'taste': 'yum'}
{'food': 'spam', 'taste': 'yum'}
>>> dict(hours=10)
{'hours': 10}
```

## Tuples

```
>>> (1, 'spam', 4, 'U')
(1, 'spam', 4, 'U')
>>> tuple('spam')
('s', 'p', 'a', 'm')
```

> Files

```
>>> open ('eggs.txt')
<open file 'eggs.txt', mode 'r' at 0x10fa3c5d0>
```

> Sets

```
>>> set('abac')
set(['a', 'c', 'b'])
```

> Help

```
>>> help('hi'.replace)
```
