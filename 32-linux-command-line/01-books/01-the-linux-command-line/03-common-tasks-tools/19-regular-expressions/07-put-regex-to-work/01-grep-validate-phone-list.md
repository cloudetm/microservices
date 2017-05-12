# Validating a Phone List with grep

## checking a list of phone numbers

> Setup - for loop to generate a list of phone numbers

```
$ for i in {1..10}; do echo "(${RANDOM:0:3}) ${RANDOM:0:3}-${RANDOM:0:4}" >> phonelist.txt; done

$ cat phonelist.txt
(187) 153-2317
(176) 121-2369
(207) 271-2170
(200) 41-3204
(191) 309-8443
(517) 310-7391
(134) 359-3544
(174) 104-1654
(672) 125-752
(215) 319-3003
```

note: some of the numbers are malformed

> Test

`-v` option - produce an inverse match

```
$ grep -Ev '^\([0-9]{3}\) [0-9]{3}-[0-9]{4}$' phonelist.txt 
(200) 41-3204
(672) 125-752
```
