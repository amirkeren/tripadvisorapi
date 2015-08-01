# Trip Advisor API
Version 1.0
Java wrapper for the Trip Advisor API, Amir Keren, 2015

This project allows for an easy integration to the Trip Advisor API using Java.

Requires - Java 1.7 and up, Maven and a valid Trip Advisor API Key
(this can be obtained by submitting a request here - https://developer-tripadvisor.com/content-api/request-api-access/)

Note that there are three ways to search for information about locations using the API -

1. Searching for the name of the location (e.g "Boston" for Boston, MA, USA)
2. Searching for the longitude & latitude coordinates of the location (e.g "42.3322833,-71.016668"  for Boston, MA, USA)                                                                             
3. Using the Trip Advisor locationId directly (e.g "60745" for Boston, MA, USA)

Example for usage (see test.Main class) - 

```TripAdvisorLocationAPI api = new TripAdvisorLocationAPI("<Trip Advisor API Key>");
List<Attraction> attractions = api.getAttractions("New York, NY, USA", new AttractionsRequest(null, null, null));
for (Attraction attraction: attractions) {
  System.out.println(attraction.getName());
}```

More information on the API itself can be found here - https://developer-tripadvisor.com/content-api/documentation/
