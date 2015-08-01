package com.tripadvisor.api.requests;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Amir Keren on 08/07/2015.
 */
public class HotelsRequest extends Request {

	public enum SubCategoryEnum {
		hotel, bb, specialty
	}

	public HotelsRequest(SubCategoryEnum subcategory, String lang, String currency) {
		super(FilterEnum.hotels, lang, currency);
		if (subcategory != null && StringUtils.isNotBlank(subcategory.name())) {
			params.set("subcategory", subcategory.name());
		}
	}

}