package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Category {

	private String name;
	private String localized_name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalized_name() {
		return localized_name;
	}

	public void setLocalized_name(String localized_name) {
		this.localized_name = localized_name;
	}

}