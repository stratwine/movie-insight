echo "" > mresponse.txt #clear contents initially
count=1
while IFS= read -r line
do
echo "Movie #"$count "requesting imdb for info about"$line
curl http://www.imdbapi.com/?t="$line" >>mresponse.txt
count=$(($count+1))
echo "info on" $count "movies retrieved"
done <mlist.txt
