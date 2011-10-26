#!/bin/bash

find . -name *.avi |
 sed "s/.*\///g" |
 sed "s/\[.*//g" |
 sed "s/(.*//g" |
 sed "s/\.avi//g" |
 sed "s/\./ /g"|
 sed "s/DVD.*//i" |
 sed "s/ /%20/g" |
 sed "s/'/%27/g" > mlist.txt

