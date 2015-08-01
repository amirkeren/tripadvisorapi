package com.tripadvisor.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Amir Keren on 13/07/2015.
 */
public class Utils {

	private static ObjectMapper mapper;

	public static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}

}