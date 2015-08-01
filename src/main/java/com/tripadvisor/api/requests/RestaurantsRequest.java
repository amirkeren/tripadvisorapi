package com.tripadvisor.api.requests;

import org.apache.commons.lang.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class RestaurantsRequest extends Request {

	public enum SubCategoryEnum {
		bakery, cafe, deli, fast_food, sit_down
	}

	public enum CuisineEnum {
		African, American, Asian, Bakery, Barbecue, British, Cafe, Caribbean, Chinese, Continental, Delicatessen,
		Dessert, European, French, German, Greek, Indian, Irish, Italian, Japanese, Mediterranean, Pizza, Pub, Seafood,
		Soups, Spanish, Steakhouse, Sushi, Thai, Vegetarian, Vietnamese
	}

	public RestaurantsRequest(SubCategoryEnum subcategory, String lang, String currency, CuisineEnum cuisines,
							  String prices) {
		super(FilterEnum.restaurants, lang, currency);
		if (subcategory != null && StringUtils.isNotBlank(subcategory.name())) {
			params.set("subcategory", subcategory.name());
		}
		if (cuisines != null && StringUtils.isNotBlank(cuisines.name())) {
			params.set("cuisines", cuisines.name());
		}
		if (StringUtils.isNotBlank(prices) && validatePriceInput(prices)) {
			params.set("prices", prices);
		}
	}

	private boolean validatePriceInput(String prices) {
		String pattern = "^[1-4]$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(prices);
		return m.matches();
	}

}