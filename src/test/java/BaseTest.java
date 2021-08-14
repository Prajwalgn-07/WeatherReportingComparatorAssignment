import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.PropertyReader;

import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    PropertyReader propertyReader;
    public BaseTest() throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/WebPageLinks.properties");
    }
    public void initializeBrowser(){
        String browser=System.getProperty("browser");
        String headless=System.getProperty("headless");
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            if(headless.equals("True")){
                ChromeOptions options=headLessChrome();
                driver=new ChromeDriver(options);
            }
            else {
                driver = new ChromeDriver();
            }
        }
        else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            if(headless.equals("True")){
                FirefoxOptions options=headLessFireFox();
                driver=new FirefoxDriver(options);
            }
            else {
                driver = new FirefoxDriver();
            }
        }
        else{
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(propertyReader.getProperty("accuWeatherPageLink"));
    }
    public ChromeOptions headLessChrome(){
        ChromeOptions options=new ChromeOptions();
        options.setHeadless(true);
        return options;
    }
    public FirefoxOptions headLessFireFox(){
        FirefoxOptions options=new FirefoxOptions();
        options.setHeadless(true);
        return options;
    }
}
