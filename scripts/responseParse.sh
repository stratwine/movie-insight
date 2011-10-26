cat mresponse.txt |
sed "s/}/\n/g"  |
awk -F: '{print $1 $2 $13 $14 $15}'|
cut -d, -f1,3,4 |
#trimming away and sorting
 sed "s/ /_/g" |
 sed "s/\"/ /g" | 
 sed "s/, //g" |
 sed "s/{ //g" |
 #sed "s/  / /g" |
 sed "s/   / /g" | 
 sort -k4 -r -n


