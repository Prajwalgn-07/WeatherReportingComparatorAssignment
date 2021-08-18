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

import java.util.ArrayList;
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
    @FindBy(css=".cur-con-weather-card__panel.details-container>div>span[class='value']")
    List<WebElement>currentWeatherDetails;
    //Advertisement webElements
    @FindBy(xpath="//*[@id=\"google_ads_iframe_/6581/web/in/interstitial/weather/local_home_0\"]")
    WebElement googleAdIframe;
    @FindBy(id="dismiss-button")
    WebElement adClose;

    public UiWeatherData  CurrentWeatherDetails(){
        visibilityOfElementLocated(5,detailedWeatherData);
        List<String>weatherDetails=getCurrentWeatherDetails(currentWeatherDetails);
        String windSpeed=weatherDetails.get(0);
        String windGusts=weatherDetails.get(1);
        detailedWeatherData.click();
        //handling the ads
        driver.switchTo().frame(googleAdIframe);
        adClose.click();
        driver.switchTo().defaultContent();
        visibilityOfElementLocated(5,currentTemperature);
        String Temperature=currentTemperature.getText();
        return setDetails(Temperature,windSpeed,windGusts);
    }
    public UiWeatherData setDetails(String Temperature,String windSpeed,String windGusts){
        UiWeatherData uiWeatherData=new UiWeatherData();
        uiWeatherData.setUiWindSpeed(windSpeed);
        uiWeatherData.setUiTemperature(Temperature);
        uiWeatherData.setUiWindGusts(windGusts);
        return uiWeatherData;
    }
    public List<String> getCurrentWeatherDetails(List<WebElement>weatherDetails){
        List<String>details=new ArrayList<>();
        if(weatherDetails.size()==4){
            details.add(weatherDetails.get(2).getText());
            details.add(weatherDetails.get(3).getText());
        }
        else{
            details.add(weatherDetails.get(1).getText());
            details.add(weatherDetails.get(2).getText());
        }
        return details;
    }
}

