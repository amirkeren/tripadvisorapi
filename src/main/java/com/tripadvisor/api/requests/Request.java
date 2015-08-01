package com.tripadvisor.api.requests;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by Amir Keren on 31/07/2015.
 */
public class Request {

    protected MultiValueMap<String, String> params;
    protected FilterEnum filter;

    public enum FilterEnum {
        attractions, hotels, restaurants
    }

    public Request(FilterEnum filter, String lang, String currency) {
        this.filter = filter;
        params = new LinkedMultiValueMap();
        if (StringUtils.isNotBlank(lang)) {
            params.set("lang", lang);
        }
        if (StringUtils.isNotBlank(currency)) {
            params.set("currency", currency);
        }
    }

    public MultiValueMap<String, String> getParams() { return params; }

    public String getFilter() { return filter.name(); }

}