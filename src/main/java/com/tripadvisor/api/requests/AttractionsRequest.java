package com.tripadvisor.api.requests;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class AttractionsRequest extends Request {

	public enum SubCategoryEnum {
		other, activities, nightlife, shopping, bars, clubs, food_drink, ranch_farm, adventure, gear_rentals,
		wellness_spas, classes, sightseeing_tours, performances, sports, outdoors, amusement, landmarks, zoos_aquariums,
		museums, cultural
	}

	public AttractionsRequest(SubCategoryEnum subcategory, String lang, String currency) {
		super(FilterEnum.attractions, lang, currency);
		if (subcategory != null && StringUtils.isNotBlank(subcategory.name())) {
			params.set("subcategory", subcategory.name());
		}
	}

}