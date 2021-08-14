package Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ApiWeatherData {
    public @Getter String apiTemperature;
    public @Getter String apiVisibility;
    public @Getter String apiHumidity;
}
