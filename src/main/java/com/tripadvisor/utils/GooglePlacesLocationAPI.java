package com.tripadvisor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.*;

/**
 * Created by Amir Keren on 18/07/2015.
 */
public class GooglePlacesLocationAPI {

	private Logger log = LoggerFactory.getLogger(GooglePlacesLocationAPI.class);

	private GooglePlaces context;

	public GooglePlacesLocationAPI(String apiKey) {
		log.debug("Initializing Google Places API");
		context = new GooglePlaces(apiKey);
		log.debug("Google Maps API Initialized");
	}

	public String getCoordinatesForLocation(String place) {
		List<Place> places = context.getPlacesByQuery(place, GooglePlaces.MAXIMUM_RESULTS);
		if (places.size() > 0) {
			log.debug("Found {} places matching search", places.size());
			return places.get(0).getLatitude() + "," + places.get(0).getLongitude();
		}
		log.error("Failed to find results");
		return null;
	}

}