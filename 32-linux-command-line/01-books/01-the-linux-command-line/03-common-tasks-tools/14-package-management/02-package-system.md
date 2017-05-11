# How a Package System Works

## Package Files

The basic unit of software in a packaging system is the package file.
A package file is a compressed collection of files that comprise the software package.
A package may consist of many programs and data files that support the programs. 

## Repositories

Most packages today are created by the distribution vendors and interested third parties. 
Packages are make available to the users of a distribution in central repositories.

## Dependencies

If a package requires a shared resource such as a shared library, it is a dependency. 

## High-and Low-level Package Tools

*Packaging System Tools*

| Distributions  | Low-Level Tools | High-Level Tools |
|----------------|-----------------|------------------|
| Debian style   | dpkg            | apt-get, aptitude |
| Fedora, CentOS | rpm             | yum |
