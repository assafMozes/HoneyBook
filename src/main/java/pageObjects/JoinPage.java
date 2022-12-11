package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JoinPage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public JoinPage(RemoteWebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }

    public boolean isJoinPage(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h2.qa-use-email"))).getText().equals("OR SIGN UP WITH EMAIL");

    }

    public void fillEmail(String email){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email"))).sendKeys(email);
    }
    public void fillFirstName(String firstName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(firstName);

    }
    public void fillLastName(String lastName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))).sendKeys(lastName);

    }
    public void filPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password"))).sendKeys(password);

    }
    public void fillBirthDay(int birthDay){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BirthDay"))).sendKeys(String.valueOf(birthDay));

    }
    public void fillBirthMonth(String birthMonth){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BirthMonth"))).sendKeys(birthMonth);

    }
    public void fillBirthYear(int birthYear){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BirthYear"))).sendKeys(String.valueOf(birthYear));

    }
    public void chooseGender(long isMale){
        if(isMale == 0){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:has(> #male )"))).click();
        }
    }

    public void clickJoinAsos(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='submit']"))).click();
    }
    public String getEmailError(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email-error"))).getText();
    }
    public String getFirstNameError(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName-error"))).getText();
    }

    public String getBirthDayError(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BirthDay-error"))).getText();
    }

}
