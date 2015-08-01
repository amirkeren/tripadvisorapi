package com.tripadvisor.api.responses;

import com.tripadvisor.api.dto.Hotel;
import java.util.List;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class HotelsResponse extends Response {

	private List<Hotel> data;

	public List<Hotel> getData() {
		return data;
	}

	public void setData(List<Hotel> data) {
		this.data = data;
	}

}