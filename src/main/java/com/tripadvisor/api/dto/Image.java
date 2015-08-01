package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class Image {

	private String tiny;
	private String small;
	private String large;

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public String getTiny() {
		return tiny;
	}

	public void setTiny(String tiny) {
		this.tiny = tiny;
	}

}