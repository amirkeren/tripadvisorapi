package com.tripadvisor.api;

import com.tripadvisor.api.dto.Attraction;
import com.tripadvisor.api.dto.Hotel;
import com.tripadvisor.api.dto.Restaurant;
import com.tripadvisor.api.requests.AttractionsRequest;
import com.tripadvisor.api.requests.Request;
import com.tripadvisor.api.responses.AttractionsResponse;
import com.tripadvisor.api.responses.HotelsResponse;
import com.tripadvisor.api.responses.MapResponse;
import com.tripadvisor.api.responses.RestaurantsResponse;
import com.tripadvisor.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class TripAdvisorLocationAPI {

	private Logger log = LoggerFactory.getLogger(TripAdvisorLocationAPI.class);

	private Utils utils;
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private String apiKey;

	/**
	 * Normal constructor, requires only the Trip Advisor API key
	 *
	 * @param apiKey Trip Advisor key
	 */
	public TripAdvisorLocationAPI(String apiKey) throws IOException {
		log.debug("Initializing Trip Advisor API");
		this.apiKey = apiKey;
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		utils = new Utils();
		log.debug("Trip Advisor API Initialized");
	}

	/**
	 * Gets the Trip Advisor locationId from the given coordinates
	 *
	 * @param coordinates A string in the format of "longitude,latitude"
	 */
	private String getLocationIdFromCoordinates(String coordinates) throws IOException {
		log.debug("Getting location id for {}", coordinates);
		String queryUrl = "http://api.tripadvisor.com/api/partner/2.0/map/" + coordinates +
				"?key=" + apiKey;
		ResponseEntity<String> response = runQueryAux(queryUrl, null);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			MapResponse result = utils.getMapper().readValue(response.getBody().getBytes(), MapResponse.class);
			if (result.getData().size() > 0) {
				return result.getData().get(0).getAncestors().get(0).getLocation_id();
			}
		}
		log.error("Failed to get id from coordinates");
		if (response != null) {
			log.error("Response - {}", response.toString());
		}
		return null;
	}

	/**
	 * Gets the top 10 most popular attractions in the given location
	 *
	 * @param location name of the place, coordinates or locationId
	 * @param request request, including optional filters (use null if no filters required)
	 */
	public List<Attraction> getAttractions(String location, AttractionsRequest request) throws IOException {
		log.debug("Getting list of attractions for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			AttractionsResponse result = utils.getMapper().readValue(response.getBody().getBytes(),
					AttractionsResponse.class);
			log.debug("Found {} attractions", result.getData().size());
			return result.getData();
		}
		log.error("Failed to get list of attractions");
		if (response != null) {
			log.error("Response - {}", response.toString());
		}
		return null;
	}

	/**
	 * Gets the top 10 most popular restaurants in the given location
	 *
	 * @param location name of the place, coordinates or locationId
	 * @param request request, including optional filters (use null if no filters required)
	 */
	public List<Restaurant> getRestaurants(String location, Request request) throws IOException {
		log.debug("Getting list of restaurants for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			RestaurantsResponse result = utils.getMapper().readValue(response.getBody().getBytes(),
					RestaurantsResponse.class);
			log.debug("Found {} restaurants", result.getData().size());
			return result.getData();
		}
		log.error("Failed to get list of restaurants");
		if (response != null) {
			log.error("Response - {}", response.toString());
		}
		return null;
	}

	/**
	 * Gets the top 10 most popular hotels in the given location
	 *
	 * @param location name of the place, coordinates or locationId
	 * @param request request, including optional filters (use null if no filters required)
	 */
	public List<Hotel> getHotels(String location, Request request) throws IOException {
		log.debug("Getting list of hotels for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			HotelsResponse result = utils.getMapper().readValue(response.getBody().getBytes(),
					HotelsResponse.class);
			log.debug("Found {} hotels", result.getData().size());
			return result.getData();
		}
		log.error("Failed to get list of hotels");
		if (response != null) {
			log.error("Response - {}", response.toString());
		}
		return null;
	}

	/**
	 * Converts the location to locationId if necessary, builds the URL and sends the request
	 *
	 * @param location name of the place, coordinates or locationId
	 * @param request request, including optional filters (use null if no filters required)
	 */
	private ResponseEntity<String> runQuery(String location, Request request) throws IOException {
		String coordinates = null, locationId;
		if (isCoordinates(location)) {
			coordinates = location;
		} else if (!isLocationId(location)) {
			coordinates = utils.getCoordinatesFromLocation(location);
		}
		if (!isLocationId(location)) {
			locationId = getLocationIdFromCoordinates(coordinates);
		} else {
			locationId = location;
		}
		String queryUrl = "http://api.tripadvisor.com/api/partner/2.0/location/" + locationId + "/" +
				request.getFilter() + "?key=" + apiKey;
		return runQueryAux(queryUrl, request);
	}

	/**
	 * Runs the actual query
	 *
	 * @param queryUrl the query URL
	 * @param request request, including optional filters (use null if no filters required)
	 */
	private ResponseEntity<String> runQueryAux(String queryUrl, Request request) {
		log.debug("Running query - {}", queryUrl);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(queryUrl);
		if (request != null) {
			builder.queryParams(request.getParams());
		}
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity(headers), String.class);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		return response;
	}

	private boolean isCoordinates(String location) {
		String pattern = "^(\\-?\\d+(\\.\\d+)?),\\s*(\\-?\\d+(\\.\\d+)?)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(location);
		return m.matches();
	}

	private boolean isLocationId(String location) {
		String pattern = "^[0-9]+$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(location);
		return m.matches();
	}

}