package com.tripadvisor.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Created by Amir Keren on 13/07/2015.
 */
public class Utils {

	private ObjectMapper mapper;
	private GeoLocations geoLocationsAmericas;
	private GeoLocations geoLocationsGlobal;

	public Utils() throws IOException {
		mapper = new ObjectMapper();
		ClassLoader classLoader = getClass().getClassLoader();
		geoLocationsAmericas = mapper.readValue(new File(classLoader.getResource("geolocations-americas.json").getFile()), GeoLocations.class);
		geoLocationsGlobal = mapper.readValue(new File(classLoader.getResource("geolocations.json").getFile()), GeoLocations.class);
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public String getCoordinatesFromLocation(String location) {
		String result = geoLocationsAmericas.getCoordinatesFromLocation(location);
		return result != null ? result : geoLocationsGlobal.getCoordinatesFromLocation(location);
	}

}