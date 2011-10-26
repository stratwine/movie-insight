#!/bin/bash

if [ -z $1];
then
echo 'Directory parameter not provided. Searching in current dir'
dir='.'
else
echo 'Searching in' $1
dir=$1
fi

find $dir -iname *.avi |
 sed "s/.*\///g" | #remove any path info
 sed "s/\[.*//g" | #remove anything staring from [
 sed "s/(.*//g" | #remove anything starting from (
 sed "s/\.avi//g" | #replace .avi with ""
 sed "s/\./ /g"| #replace . with space
 sed "s/DVD.*//i" |  #remove anything after DVD, case insensitive
 sed "s/XviD.*//i"| #remove anything after XviD, case insensitive
 sed "s/Rip.*//i" #remove anything starting with rip
# sed "s/ /%20/g" |
# sed "s/'/%27/g" > mlist.txt

