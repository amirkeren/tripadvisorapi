package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Attraction extends TripAdvisorLocation {

	private Category[] attraction_types;
	private WikipediaInfo wikipedia_info;

	public Category[] getAttraction_types() {
		return attraction_types;
	}

	public void setAttraction_types(Category[] attraction_types) {
		this.attraction_types = attraction_types;
	}

	public WikipediaInfo getWikipedia_info() {
		return wikipedia_info;
	}

	public void setWikipedia_info(WikipediaInfo wikipedia_info) {
		this.wikipedia_info = wikipedia_info;
	}

}