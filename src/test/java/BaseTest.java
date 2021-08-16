import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.PropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    public static WebDriver driver;
    PropertyReader propertyReader;
    public BaseTest() throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/WebPage.properties");
    }
    public void initializeBrowser(){
        String browser=System.getProperty("browser");
        String headless=System.getProperty("headless");
        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            Map<String,Object>prefs=new HashMap<>();
            prefs.put("profile.default_context_setting_values.notifications",propertyReader.getProperty("Notification"));
            ChromeOptions options=new ChromeOptions();
            options.setExperimentalOption("prefs",prefs);
            if(headless.equals("True")){
                options.setHeadless(true);
            }
            driver=new ChromeDriver(options);
        }
        else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile=new FirefoxProfile();
            profile.setPreference("permissions.default.desktop-notification", propertyReader.getProperty("Notification"));
            FirefoxOptions firefoxOptions=new FirefoxOptions();
            firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
            if(headless.equals("True")){
                firefoxOptions.setCapability("headless",true);
            }
            driver=new FirefoxDriver(firefoxOptions);
        }
        else{
            WebDriverManager.chromedriver().setup();
            Map<String,Object>prefs=new HashMap<>();
            prefs.put("profile.default_context_setting_values.notifications",propertyReader.getProperty("Notification"));
            ChromeOptions options=new ChromeOptions();
            options.setExperimentalOption("prefs",prefs);
            driver=new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.get(propertyReader.getProperty("accuWeatherPageLink"));
    }
    public double doublePartInString(String data){
        return Double.parseDouble(data.replaceAll("[^0-9\\.]", "").trim());
    }
}
