package pages;

import Data.UiWeatherData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

import java.util.List;

public class WeatherPage extends Waits{
    WebDriver driver;
    public WeatherPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "/html/body/div/div[5]/div[1]/div[1]/a[1]/div[2]/span[2]")
    WebElement detailedWeatherData;
    @FindBy(xpath = "/html/body/div/div[5]/div[1]/div[1]/div[2]/div[2]/div[1]/div")
    WebElement currentTemperature;
    @FindBy(xpath = "/html/body/div/div[5]/div[1]/div[1]/div[2]/div[4]/div[1]/div[5]/div[2]")
    WebElement currentHumidity;
    @FindBy(xpath="/html/body/div/div[5]/div[1]/div[1]/div[2]/div[4]/div[2]/div[4]/div[2]")
    WebElement currentVisibility;
    //Advertisement webElements
    @FindBy(xpath="//*[@id=\"google_ads_iframe_/6581/web/in/interstitial/weather/local_home_0\"]")
    WebElement googleAdIframe;
    @FindBy(id="dismiss-button")
    WebElement adClose;

    public UiWeatherData  CurrentWeatherDetails(){
        visibilityOfElementLocated(5,detailedWeatherData);
        detailedWeatherData.click();
        //handling the ads
        driver.switchTo().frame(googleAdIframe);
        adClose.click();
        driver.switchTo().defaultContent();
        String Temperature=currentTemperature.getText();
        String Humidity=currentHumidity.getText();
        String Visibility=currentVisibility.getText();
        return setDetails(Temperature,Humidity,Visibility);
    }
    public UiWeatherData setDetails(String Temperature,String Humidity,String Visibility){
        UiWeatherData uiWeatherData=new UiWeatherData();
        uiWeatherData.setUiHumidity(Humidity);
        uiWeatherData.setUiVisibility(Visibility);
        uiWeatherData.setUiTemperature(Temperature);
        return uiWeatherData;
    }
}

