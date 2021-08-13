package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
    WebDriver driver;
    public Waits(WebDriver driver){
        this.driver=driver;
    }
    public  WebDriverWait setWait(int timeOutInSeconds){
        return new WebDriverWait(driver,timeOutInSeconds);
    }
    public void visibilityOfElementLocated(int timeOutInSeconds, WebElement visibilityOfElement){
        setWait(timeOutInSeconds).until(ExpectedConditions.visibilityOf(visibilityOfElement));
    }
}
