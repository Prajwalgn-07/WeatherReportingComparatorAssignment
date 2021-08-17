import Data.ApiWeatherData;
import Data.UiWeatherData;
import DetailsUsingApi.GetWeatherDetails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.WeatherPage;
import utils.PropertyReader;

import java.io.File;
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
    @Parameters("city")
    public void getDetails(String city) throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/Variance.properties");
        new HomePage(driver).searchLocation(city);
        uiWeatherData=new WeatherPage(driver).CurrentWeatherDetails();
        apiWeatherData=new GetWeatherDetails().getWeatherDetails(city);
        this.temperatureDifference=Math.abs((doublePartInString(uiWeatherData.getUiTemperature())
                -doublePartInString(apiWeatherData.getApiTemperature())));
        this.humidityDifference=Math.abs(doublePartInString(uiWeatherData.getUiHumidity())
                                    -doublePartInString(apiWeatherData.getApiHumidity()));
        this.visibilityDifference=Math.abs(doublePartInString(uiWeatherData.getUiVisibility())
                                  -doublePartInString(apiWeatherData.getApiVisibility()));
    }
    @Test
    public void temperatureVariance() throws IOException {
        Assert.assertTrue(temperatureDifference < Double.parseDouble(propertyReader.getProperty("temperatureVariance")));
    }
    @Ignore
    @Test
    public void humidityVariance(){
        Assert.assertTrue(humidityDifference < Double.parseDouble(propertyReader.getProperty("humidityVariance")));
    }
    @Ignore
    @Test
    public void visibilityVariance(){
        Assert.assertTrue(visibilityDifference < Double.parseDouble(propertyReader.getProperty("visibilityVariance")));
    }
    @AfterMethod
    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            TakesScreenshot screenshot=((TakesScreenshot) driver);
            File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile=new File("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/java/utils/FailedTestCases/"+".jpeg");
            try {
                FileUtils.copyFile(sourceFile,destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
}
