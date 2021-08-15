import Data.ApiWeatherData;
import Data.UiWeatherData;
import DetailsUsingApi.GetWeatherDetails;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WeatherPage;

import java.io.IOException;

public class ComparatorTest extends BaseTest{
    public ComparatorTest() throws IOException {
        super();
        initializeBrowser();
    }
    @Test
    public void main() throws IOException {
        new HomePage(driver).searchLocation("Bangalore");
        UiWeatherData uiWeatherData=new WeatherPage(driver).CurrentWeatherDetails();
        ApiWeatherData apiWeatherData=new GetWeatherDetails().getWeatherDetails("Bangalore");
        System.out.println("The Temperature difference between ui and api is "+ Math.abs((doublePartInString(uiWeatherData.getUiTemperature())
        -doublePartInString(apiWeatherData.getApiTemperature())))+"Centigrade");
        System.out.println("The Humidity difference between ui and api is "+Math.abs(doublePartInString(uiWeatherData.getUiHumidity())
                -doublePartInString(apiWeatherData.getApiHumidity()))+"%");
        System.out.println("The Visibility difference between ui and api is "+Math.abs(doublePartInString(uiWeatherData.getUiVisibility())
        -doublePartInString(apiWeatherData.getApiVisibility()))+"km");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
