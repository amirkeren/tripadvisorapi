package com.tripadvisor.utils;

import java.util.List;

/**
 * Created by Amir Keren on 01/08/2015.
 */
public class GeoLocations {

    private List<GeoLocationInfo> data;

    public List<GeoLocationInfo> getData() {
        return data;
    }

    public void setData(List<GeoLocationInfo> data) {
        this.data = data;
    }

    public String getCoordinatesFromLocation(String location) {
        for (GeoLocationInfo geoLocation: getData()) {
            if (location.toLowerCase().contains(geoLocation.getCapital().toLowerCase())) {
                return geoLocation.getCapital_latitude() + "," + geoLocation.getCapital_longitude();
            }
        }
        return null;
    }

}

class GeoLocationInfo {

    private String country;
    private String country_code;
    private String state;
    private String state_code;
    private String capital;
    private String capital_latitude;
    private String capital_longitude;
    private String latitude;
    private String longitude;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapital_latitude() {
        return capital_latitude;
    }

    public void setCapital_latitude(String capital_latitude) {
        this.capital_latitude = capital_latitude;
    }

    public String getCapital_longitude() {
        return capital_longitude;
    }

    public void setCapital_longitude(String capital_longitude) {
        this.capital_longitude = capital_longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}