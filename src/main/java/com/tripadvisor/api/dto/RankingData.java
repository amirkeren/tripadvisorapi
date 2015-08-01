package com.tripadvisor.api.dto;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class RankingData {

	private String geo_location_id;
	private String ranking_string;
	private String geo_location_name;
	private String ranking_out_of;
	private String ranking;

	public String getGeo_location_id() {
		return geo_location_id;
	}

	public void setGeo_location_id(String geo_location_id) {
		this.geo_location_id = geo_location_id;
	}

	public String getRanking_string() {
		return ranking_string;
	}

	public void setRanking_string(String ranking_string) {
		this.ranking_string = ranking_string;
	}

	public String getGeo_location_name() {
		return geo_location_name;
	}

	public void setGeo_location_name(String geo_location_name) {
		this.geo_location_name = geo_location_name;
	}

	public String getRanking_out_of() {
		return ranking_out_of;
	}

	public void setRanking_out_of(String ranking_out_of) {
		this.ranking_out_of = ranking_out_of;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

}