package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    RemoteWebDriver driver;
    WebDriverWait wait;
    String URL = "https://www.asos.com/";
    public MainPage(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5);

    }
    public boolean isHomePage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.genericSplash__title span.highlight"))).getText().equals("This is ASOS");

    }
    public void clickMyAccountIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='myAccountDropdown'] /button"))).click();
    }

    public void clickJoin(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='myAccountDropdown'] //a[@data-testid='signup-link']"))).click();
    }

    public boolean isLoggedIn(String userName){
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(),'Hi " + userName + "')]"))).size() == 1;
    }
}
