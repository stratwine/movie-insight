#!/bin/bash

if [ -z $1 ];
then
echo 'Directory parameter not provided. Searching in current dir'
dir='.'
else
echo 'Searching in' $1
dir=$1
fi

find $dir -iname *.avi |
 sed "s/.*\///g" | #remove any path info
 sed "s/\./ /g"| #replace . with space
 sed "s/\[.*//g; s/(.*//g; s/DVD.*//i; s/xvid.*//i; s/rip.*//i; s/.avi//g" 
