package Data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
public class UiWeatherData {
    public @Getter @Setter String uiTemperature;
    public @Getter @Setter String uiVisibility;
    public @Getter @Setter String uiHumidity;
}
