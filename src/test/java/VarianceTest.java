import Data.ApiWeatherData;
import Data.UiWeatherData;
import DetailsUsingApi.GetWeatherDetails;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WeatherPage;
import utils.PropertyReader;

import java.io.IOException;

public class VarianceTest extends BaseTest{
    public VarianceTest() throws IOException {
        super();
        initializeBrowser();
    }
    PropertyReader propertyReader;
    UiWeatherData uiWeatherData;
    ApiWeatherData apiWeatherData;
    public double temperatureDifference;
    public double humidityDifference;
    public double visibilityDifference;
    @BeforeMethod
    public void getDetails() throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/Variance.properties");
        new HomePage(driver).searchLocation("Bangalore");
        uiWeatherData=new WeatherPage(driver).CurrentWeatherDetails();
        apiWeatherData=new GetWeatherDetails().getWeatherDetails("Bangalore");
        this.temperatureDifference=Math.abs((doublePartInString(uiWeatherData.getUiTemperature())
                -doublePartInString(apiWeatherData.getApiTemperature())));
        this.humidityDifference=Math.abs(doublePartInString(uiWeatherData.getUiHumidity())
                                    -doublePartInString(apiWeatherData.getApiHumidity()));
        this.visibilityDifference=Math.abs(doublePartInString(uiWeatherData.getUiVisibility())
                                  -doublePartInString(apiWeatherData.getApiVisibility()));
    }
    @Ignore
    @Test
    public void temperatureVariance() throws IOException {
        Assert.assertTrue(temperatureDifference < Double.parseDouble(propertyReader.getProperty("temperatureVariance")));
    }
    @Test
    public void humidityVariance(){
        Assert.assertTrue(humidityDifference <= Double.parseDouble(propertyReader.getProperty("humidityVariance")));
    }
    @Ignore
    @Test
    public void visibilityVariance(){
        Assert.assertTrue(visibilityDifference > Double.parseDouble(propertyReader.getProperty("visibilityVariance")));
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
