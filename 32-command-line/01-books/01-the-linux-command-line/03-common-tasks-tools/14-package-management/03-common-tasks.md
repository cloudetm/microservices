# Common Package Management Tasks

## Finding a Package in a Repository

> Package Search Commands

| Style   | Command(s)  |
|---------|-------------|
| Debian  | apt-get update; apt-cache search search_string |
| Red Hat | yum search search_string |

*Example - centos*

```
yum search emacs
```

## Installing a Package from a Repository

> High-level Package Installation Commands

| Style   | Command(s)  |
|---------|-------------|
| Debian  | apt-get update; apt-get install package_name |
| Red Hat | yum install package_name |

*Example - centos*

```
yum install emacs
```

## Installing a Package from a Package File

> Low-Level Package Installation Commands

| Style   | Command(s)  |
|---------|-------------|
| Debian  | dpkg --install package_file |
| Red Hat | rpm -ipackage_file |

*Example - centos*

```
rpm -i emacs-22.1-7.fc7-i386.rpm
```

## Removing a Package

> Package Removal Commands

| Style   | Command(s)  |
|---------|-------------|
| Debian  | apt-get remove package_name |
| Red Hat | yum erase package_name      |

*Example - centos*

```
yum erase emacs
```

## Updating Packages from a Repository

> Package Update Commands

| Style   | Command  |
|---------|-------------|
| Debian  | apt-get update; apt-get upgrade |
| Red Hat | yum update |

## Upgrading a Package from a Package File

> Low-Level Package Upgrade Commands

| Style   | Command     |
|---------|-------------|
| Debian  | dpkg --install package_file |
| Red Hat | rpm -U package_file |

*Example - centos*

```
rpm -U emacs-22.1-7.fc7-i386.rpm
```

## Listing Installed Packages

> Package Listing Commands

| Style   | Command     |
|---------|-------------|
| Debian  | dpkg --list |
| Red Hat | rpm -qa     |

## Determining Whether a Package Is Installed

> Package Status Commands

| Style   | Command     |
|---------|-------------|
| Debian  | dpkg --status package_name |
| Red Hat | rpm -q package_name |

*Example - centos*

```
$ rpm -q emacs
package emacs is not installed
```

## Displaying information About an Installed Package

> Package Information Commands

| Style   | Command     |
|---------|-------------|
| Debian  | apt-cache show package_name |
| Red Hat | yum info package_name |

*Example - centos*

```
$ yum info nano
Loaded plugins: fastestmirror
Loading mirror speeds from cached hostfile
 * base: centos.unixheads.org
 * extras: ftp.osuosl.org
 * updates: repos-lax.psychz.net
Installed Packages
Name        : nano
Arch        : x86_64
Version     : 2.3.1
Release     : 10.el7
Size        : 1.6 M
Repo        : installed
From repo   : base
Summary     : A small text editor
URL         : http://www.nano-editor.org
License     : GPLv3+
Description : GNU nano is a small and friendly text editor.
```

## Finding Which Package Installed a File

| Style   | Command     |
|---------|-------------|
| Debian  | dpkg --search file_name |
| Red hat | rpm -qf file_name |

*Example - centos*

```
$ rpm -qf /usr/bin/nano
nano-2.3.1-10.el7.x86_64
```
