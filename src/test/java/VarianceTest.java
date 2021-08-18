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
    }
    PropertyReader propertyReader;
    UiWeatherData uiWeatherData;
    ApiWeatherData apiWeatherData;

    @BeforeMethod
    @Parameters("city")
    public void getDetails(String city) throws IOException {
        initializeBrowser();
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/Variance.properties");
        new HomePage(driver).searchLocation(city);
        uiWeatherData=new WeatherPage(driver).CurrentWeatherDetails();
        apiWeatherData=new GetWeatherDetails().getWeatherDetails(city);
        setDifference(uiWeatherData,apiWeatherData);
    }
    @Test
    public void varianceTest() throws IOException {
        Assert.assertTrue(temperatureDifference < Double.parseDouble(propertyReader.getProperty("temperatureVariance")));
        Assert.assertTrue(windSpeedDifference < Double.parseDouble(propertyReader.getProperty("windSpeedVariance")));
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
