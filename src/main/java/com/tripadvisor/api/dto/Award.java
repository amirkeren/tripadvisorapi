package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Award {

	private String award_type;
	private String year;
	private Image images;
	private String[] categories;
	private String display_name;

	public String getAward_type() {
		return award_type;
	}

	public void setAward_type(String award_type) {
		this.award_type = award_type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Image getImages() {
		return images;
	}

	public void setImages(Image images) {
		this.images = images;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

}