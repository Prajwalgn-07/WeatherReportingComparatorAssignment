package Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UiWeatherData {
    public @Getter String uiTemperature;
    public @Getter String uiVisibility;
    public @Getter String uiHumidity;
}
