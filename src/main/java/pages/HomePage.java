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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends Waits {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "search-input")
    WebElement searchBox;
    @FindBy(xpath = "/html/body/div/div[1]/div[3]/div[2]/div[2]")
    WebElement resultContainer;
    @FindBy(xpath = "/html/body/div/div[1]/div[3]/div[2]/div[2]/div[1]")
    WebElement firstSearchResult;

    public void searchLocation(String place) {
        searchBox.click();
        searchBox.sendKeys(place);
        visibilityOfElementLocated(5,resultContainer);
        Actions actions=new Actions(driver);
        actions.moveToElement(firstSearchResult).click().build().perform();
    }
}

