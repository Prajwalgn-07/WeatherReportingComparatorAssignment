import DetailsUsingApi.GetWeatherDetails;

import java.io.IOException;

public class working {

    public static void main(String[] args) throws IOException {
        new GetWeatherDetails().getWeatherDetails("Mangalore");
    }
}
