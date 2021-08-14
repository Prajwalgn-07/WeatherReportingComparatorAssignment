package pages;

import Data.UiWeatherData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

public class WeatherPage{
    WebDriver driver;
    public WeatherPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "/html/body/div/div[5]/div[1]/div[1]/a[1]/div[2]/span[2]")
    WebElement detailedWeatherData;
    @FindBy(className = "temp")
    WebElement currentTemperature;
    @FindBy(xpath = "/html/body/div/div[5]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[2]")
    WebElement currentHumidity;
    @FindBy(xpath="/html/body/div/div[5]/div[1]/div[1]/div[2]/div[4]/div[2]/div[4]/div[2]")
    WebElement currentVisibility;
    @FindBy(xpath="//*[@id=\"google_ads_iframe_/6581/web/in/interstitial/weather/local_home_0\"]")
    WebElement googleAdIframe;
    @FindBy(id="dismiss-button")
    WebElement adClose;

    public UiWeatherData CurrentWeatherDetails(){
        Waits waits=new Waits(driver);
        waits.visibilityOfElementLocated(5,detailedWeatherData);
        detailedWeatherData.click();
        driver.switchTo().frame(googleAdIframe);
        adClose.click();
        String Temperature=currentTemperature.getText();
        String Humidity=currentHumidity.getText();
        String Visibility=currentVisibility.getText();
        return new UiWeatherData(Temperature,Visibility,Humidity);
    }
}

