FROM ubuntu:14.04

VOLUME ["/john2"]

ADD file1 /john2/file1

CMD ["/bin/sh"]
