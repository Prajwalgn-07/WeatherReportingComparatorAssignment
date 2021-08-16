package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waits;

import java.util.List;

public class HomePage extends Waits{
    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(className = "search-input")
    WebElement searchBox;
    @FindBy(className = "results-container")
    List<WebElement>searchBarResults;
    @FindBy(className = "results-container")
    WebElement results;
    public void searchLocation(String place){
        searchBox.click();
        searchBox.sendKeys(place);
        visibilityOfElementLocated(5,results);
        for(WebElement webElement:searchBarResults){
            String[]splitPlace=webElement.getText().split(",");
            if(splitPlace[0].contains(place)){
            webElement.click();
            break;
            }
        }
    }
}
