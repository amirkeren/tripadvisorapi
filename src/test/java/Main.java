import com.tripadvisor.api.TripAdvisorLocationAPI;
import com.tripadvisor.api.dto.Attraction;
import com.tripadvisor.api.requests.AttractionsRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Amir Keren on 31/07/2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        TripAdvisorLocationAPI api = new TripAdvisorLocationAPI("<Your Trip Advisor API Key Here>");
        List<Attraction> attractions = api.getAttractions("Boston, MA, USA", new AttractionsRequest(null, null, null));
        for (Attraction attraction: attractions) {
            System.out.println(attraction.getName());
        }
    }

}