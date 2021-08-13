package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(className = "search-input")
    WebElement searchBox;
    public void searchLocation(String place){
        searchBox.sendKeys(place);
        searchBox.sendKeys(Keys.RETURN);
    }
}
