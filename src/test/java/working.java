import Data.UiWeatherData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.WeatherPage;

import java.util.HashMap;
import java.util.Map;

public class working {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        HomePage homePage=new HomePage(driver);
        WeatherPage weatherPage=new WeatherPage(driver);
        driver.get("https://www.accuweather.com/");
        homePage.searchLocation("bangalore");
        UiWeatherData uiWeatherData=weatherPage.CurrentWeatherDetails();
        System.out.println(uiWeatherData);
    }
}
