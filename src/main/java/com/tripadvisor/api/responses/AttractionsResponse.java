package com.tripadvisor.api.responses;

import com.tripadvisor.api.dto.Attraction;

import java.util.List;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class AttractionsResponse extends Response {

	private List<Attraction> data;

	public List<Attraction> getData() {
		return data;
	}

	public void setData(List<Attraction> data) {
		this.data = data;
	}

}