package com.tripadvisor.api;

import com.tripadvisor.api.dto.Attraction;
import com.tripadvisor.api.dto.Hotel;
import com.tripadvisor.api.dto.Restaurant;
import com.tripadvisor.api.requests.Request;
import com.tripadvisor.api.responses.AttractionsResponse;
import com.tripadvisor.api.responses.HotelsResponse;
import com.tripadvisor.api.responses.MapResponse;
import com.tripadvisor.api.responses.RestaurantsResponse;
import com.tripadvisor.utils.GooglePlacesLocationAPI;
import com.tripadvisor.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.List;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class TripAdvisorLocationAPI {

	private Logger log = LoggerFactory.getLogger(TripAdvisorLocationAPI.class);

	private GooglePlacesLocationAPI googlePlacesAPI;
	private RestTemplate restTemplate;
	private HttpHeaders headers;
	private String apiKey;

	public TripAdvisorLocationAPI(String apiKey) {
		log.debug("Initializing Trip Advisor API");
		this.apiKey = apiKey;
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		log.debug("Trip Advisor API Initialized");
	}

	public TripAdvisorLocationAPI(String apiKey, String googleAPI) {
		log.debug("Initializing Trip Advisor API");
		this.apiKey = apiKey;
		googlePlacesAPI = new GooglePlacesLocationAPI(googleAPI);
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		log.debug("Trip Advisor API Initialized");
	}

	private String getLocationIdFromCoordinates(String coordinates) throws IOException {
		log.debug("Getting location id for {}", coordinates);
		String queryUrl = "http://api.tripadvisor.com/api/partner/2.0/map/" + coordinates +
				"?key=" + apiKey;
		ResponseEntity<String> response = runQueryAux(queryUrl, null);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			MapResponse result = Utils.getMapper().readValue(response.getBody().getBytes(), MapResponse.class);
			return result.getData().get(0).getAncestors().get(0).getLocation_id();
		}
		log.error("Failed to get id from coordinates");
		if (response != null) {
			log.error("Response - {}", response.toString());
		}
		return null;
	}

	public List<Attraction> getAttractions(String location, Request request) throws IOException {
		log.debug("Getting list of attractions for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			AttractionsResponse result = Utils.getMapper().readValue(response.getBody().getBytes(),
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

	public List<Restaurant> getRestaurants(String location, Request request) throws IOException {
		log.debug("Getting list of restaurants for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			RestaurantsResponse result = Utils.getMapper().readValue(response.getBody().getBytes(),
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

	public List<Hotel> getHotels(String location, Request request) throws IOException {
		log.debug("Getting list of hotels for {}", location);
		ResponseEntity<String> response = runQuery(location, request);
		if (response != null && response.getStatusCode() == HttpStatus.OK) {
			HotelsResponse result = Utils.getMapper().readValue(response.getBody().getBytes(),
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

	private ResponseEntity<String> runQuery(String location, Request request) throws IOException {
		String locationId;
		if (googlePlacesAPI != null && !isLocationId(location)) {
			String coordinates = googlePlacesAPI.getCoordinatesForLocation(location);
			locationId = getLocationIdFromCoordinates(coordinates);
		} else if (!isLocationId(location)) {
			locationId = getLocationIdFromCoordinates(location);
		} else {
			locationId = location;
		}
		String queryUrl = "http://api.tripadvisor.com/api/partner/2.0/location/" + locationId + "/" +
				request.getFilter() + "?key=" + apiKey;
		return runQueryAux(queryUrl, request);
	}

	private boolean isLocationId(String location) {
		try {
			Integer.parseInt(location);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

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

}