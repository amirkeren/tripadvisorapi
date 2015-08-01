package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Map extends TripAdvisorLocation {

	private String distance;
	private String bearing;
	private WikipediaInfo wikipedia_info;
	private Category[] attraction_types;
	private String price_level;
	private Category[] cuisine;

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getBearing() {
		return bearing;
	}

	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	public Category[] getAttraction_types() {
		return attraction_types;
	}

	public void setAttraction_types(Category[] attraction_types) {
		this.attraction_types = attraction_types;
	}

	public String getPrice_level() {
		return price_level;
	}

	public void setPrice_level(String price_level) {
		this.price_level = price_level;
	}

	public Category[] getCuisine() {
		return cuisine;
	}

	public void setCuisine(Category[] cuisine) {
		this.cuisine = cuisine;
	}

	public WikipediaInfo getWikipedia_info() {
		return wikipedia_info;
	}

	public void setWikipedia_info(WikipediaInfo wikipedia_info) {
		this.wikipedia_info = wikipedia_info;
	}

}