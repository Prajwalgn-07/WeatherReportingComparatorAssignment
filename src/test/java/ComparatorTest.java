import Data.ApiWeatherData;
import Data.UiWeatherData;
import DetailsUsingApi.GetWeatherDetails;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WeatherPage;
import java.io.File;
import java.io.IOException;

public class ComparatorTest extends BaseTest{
    @DataProvider(name = "cityNames")
    public Object[][] cities(){
        return new Object[][]{{"Bengaluru"},{"Mumbai"},{"Kolkata"},{"Delhi"}
        };
    }
    public ComparatorTest() throws IOException {
        super();
    }
    @BeforeMethod
    public void setUp(){
        initializeBrowser();
    }
    @Test(dataProvider = "cityNames")
    public void main(String cityNames) throws IOException {
        new HomePage(driver).searchLocation(cityNames);
        UiWeatherData uiWeatherData=new WeatherPage(driver).CurrentWeatherDetails();
        ApiWeatherData apiWeatherData=new GetWeatherDetails().getWeatherDetails(cityNames);
        setDifference(uiWeatherData,apiWeatherData);
        System.out.println("The temperature difference between ui and api is: "+String.format("%,.2f",temperatureDifference));
        System.out.println("The wind speed difference between ui and api is: "+String.format("%,.2f",windSpeedDifference));
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
