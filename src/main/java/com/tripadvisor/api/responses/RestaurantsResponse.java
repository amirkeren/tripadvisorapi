package com.tripadvisor.api.responses;

import com.tripadvisor.api.dto.Restaurant;
import java.util.List;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class RestaurantsResponse extends Response {

	private List<Restaurant> data;

	public List<Restaurant> getData() {
		return data;
	}

	public void setData(List<Restaurant> data) {
		this.data = data;
	}

}