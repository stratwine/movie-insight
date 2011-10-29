package com.github.minsight.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ImdbEntry {

	@JsonProperty("Title")
	String title;
	@JsonProperty("Year")
	String year;
	@JsonProperty("Rated")
	String rated;
	@JsonProperty("Released")
	String released;
	@JsonProperty("Genre")
	String genre;
	@JsonProperty("Director")
	String director;
	@JsonProperty("Writer")
	String writer;
	@JsonProperty("Actors")
	String actors;
	@JsonProperty("Plot")
	String plot;
	@JsonProperty("Poster")
	String poster;
	@JsonProperty("Runtime")
	String runtime;
	@JsonProperty("Rating")
	String rating;
	@JsonProperty("Votes")
	String votes;
	@JsonProperty("ID")
	String id;
	@JsonProperty("Response")
	String response;

	public String getTitle() {
		return title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getVotes() {
		return votes;
	}

	public void setVotes(String votes) {
		this.votes = votes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return this.title + "  " + this.rating + "  " + this.votes + "\n";
	}

}
