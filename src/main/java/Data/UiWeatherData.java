package Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UiWeatherData {
    public @Getter String Temperature;
    public @Getter String Visibility;
    public @Getter String Humidity;
}
