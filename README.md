# Trip Advisor API
Version 1.0
Java wrapper for the Trip Advisor API, Amir Keren, 2015

This project allows for an easy integration to the Trip Advisor API using Java.

Requires - Java 1.7 and up, Maven and a valid Trip Advisor API Key
(this can be obtained by submitting a request here - https://developer-tripadvisor.com/content-api/request-api-access/)

Note that there are two ways to search for information about locations using the API -

1. Initializing the API with a Google API key to allow the tool to convert the name of the destination to longitude & latitude coordinates and from there the tool will use Trip Advisor's API to get the locationId
(Google API key can be obtained here - https://code.google.com/apis/console/)
2. Searching for the longitude & latitude coordinates
3. Using the Trip Advisor locationId directly (e.g 60745 for Boston, MA)

Example for usage (see test.Main class) - 

```TripAdvisorLocationAPI api = new TripAdvisorLocationAPI("<Trip Advisor API Key>", "<Google API Key>");
List<Attraction> attractions = api.getAttractions("New York, NY, USA", new AttractionsRequest(null, null, null));
for (Attraction attraction: attractions) {
  System.out.println(attraction.getName());
}```

More information on the API itself can be found here - https://developer-tripadvisor.com/content-api/documentation/
