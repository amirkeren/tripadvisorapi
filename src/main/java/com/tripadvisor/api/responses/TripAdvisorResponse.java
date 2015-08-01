package com.tripadvisor.api.responses;

import com.tripadvisor.api.dto.TripAdvisorLocation;

import java.util.List;

/**
 * Created by Amir Keren on 18/07/2015.
 */
public class TripAdvisorResponse extends Response {

	private List<TripAdvisorLocation> data;

	public List<TripAdvisorLocation> getData() {
		return data;
	}

	public void setData(List<TripAdvisorLocation> data) {
		this.data = data;
	}

}
