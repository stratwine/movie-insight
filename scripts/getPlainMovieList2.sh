#!/bin/bash

find . -name *.avi |
 sed "s/.*\///g" | #trim away the path info
 sed "s/\[.*//g" | #remove any name part after [
 sed "s/(.*//g" | # remove any name part after (
 sed "s/\.avi//g" | # remove any name part after .avi
 sed "s/\./ /g"| # replace . with space
 sed "s/DVD.*//i" > plainList.txt
# sed "s/ /%20/g" |
# sed "s/'/%27/g" > mlist.txt

