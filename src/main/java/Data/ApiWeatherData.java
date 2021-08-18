package Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ApiWeatherData {
    public @Getter @Setter String apiTemperature;
    public @Getter @Setter String apiWindSpeed;
}
