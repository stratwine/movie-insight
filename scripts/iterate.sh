echo "" > mresponse.txt
while IFS= read -r line
do
curl http://www.imdbapi.com/?t="$line" >>mresponse.txt
done <mlist.txt
