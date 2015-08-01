package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Restaurant extends TripAdvisorLocation {

	private String price_level;
	private Category[] cuisine;

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

}