import Data.ApiWeatherData;
import Data.UiWeatherData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.PropertyReader;

import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    PropertyReader propertyReader;
    public double temperatureDifference;
    public double windSpeedDifference;
    public BaseTest() throws IOException {
        propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/WebPage.properties");
    }
    public void initializeBrowser(){
        String browser=System.getProperty("browser");
         if(browser==null){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--allow-insecure-localhost");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            driver=new ChromeDriver(options);
        }
        else if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--allow-insecure-localhost");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            if(Boolean.getBoolean("headless")){
                //To bypass access denying for headless browser
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
                options.setHeadless(true);
            }
            driver=new ChromeDriver(options);
        }
        else if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxProfile profile=new FirefoxProfile();
            profile.setPreference("permissions.default.desktop-notification", Integer.parseInt(propertyReader.getProperty("Notification")));
            FirefoxOptions firefoxOptions=new FirefoxOptions();
            firefoxOptions.setCapability(FirefoxDriver.PROFILE, profile);
            if(Boolean.getBoolean("headless")){
                firefoxOptions.setHeadless(true);
            }
            driver=new FirefoxDriver(firefoxOptions);
        }
        driver.manage().window().maximize();
        driver.get(propertyReader.getProperty("accuWeatherPageLink"));
    }
    public double doublePartInString(String data){
        return Double.parseDouble(data.replaceAll("[^0-9\\.]", "").trim());
    }
    public void setDifference(UiWeatherData uiWeatherData,ApiWeatherData apiWeatherData) throws IOException {
        PropertyReader propertyReader=new PropertyReader("/Users/prajwal/Desktop/testvagrant /assignment projects/WeatherReportingComparatorAssignment/src/main/resources/api.properties");
        if(propertyReader.getProperty("units").equals("metric")){
            this.temperatureDifference=Math.abs((doublePartInString(uiWeatherData.getUiTemperature())
                    -doublePartInString(apiWeatherData.getApiTemperature())));
            this.windSpeedDifference=Math.abs(doublePartInString(uiWeatherData.getUiWindSpeed())
                    -(doublePartInString(apiWeatherData.getApiWindSpeed())*doublePartInString(propertyReader.getProperty("m/sTokm/h"))));
        }
        else if(propertyReader.getProperty("units").equals("imperial")){
            this.temperatureDifference=Math.abs((doublePartInString(uiWeatherData.getUiTemperature())
            -(doublePartInString(apiWeatherData.getApiTemperature())-doublePartInString(
            propertyReader.getProperty("fahrenheitToCelsiusSubtractor")))/doublePartInString(
            propertyReader.getProperty("fahrenheitToCelsiusDivisor"))));
            this.windSpeedDifference=Math.abs(doublePartInString(uiWeatherData.getUiWindSpeed())
            -(doublePartInString(apiWeatherData.getApiWindSpeed())*doublePartInString(propertyReader.getProperty("miles/hrTokm/h"))));
        }
    }
}
