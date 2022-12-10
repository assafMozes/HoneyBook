import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageObjects.JoinPage;
import pageObjects.MainPage;

public class AsosTest {
    private static RemoteWebDriver driver;
    String firstName = RandomString.randomString(8, false),
            lastName = RandomString.randomString(8, false),
            emailAdress = firstName + "-" + lastName + "@honeybook.com",
            asosPassword = RandomString.randomString(10, true);

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        //Assume skips the test if no driver was created
        Assume.assumeNotNull(driver);
        driver.get("http://www.asos.com");
    }

    @Test
    public void asosTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        Assume.assumeTrue(mainPage.isHomePage()); //skips the test if we got to the wrong page

        mainPage.clickMyAccountIcon();
        mainPage.clickJoin();

        JoinPage joinPage = new JoinPage(driver);
        Assume.assumeTrue(joinPage.isJoinPage()); //skips the test if we got to the wrong page

        joinPage.fillEmail(emailAdress);
        joinPage.fillFirstName(firstName);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthDay(1);
        joinPage.fillBirthMonth("January");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        //from here ASOS blocks Selenium and this is theoretical
        Assert.assertTrue(mainPage.isHomePage()); //back to the main page assertion
        mainPage.clickMyAccountIcon();
        Assert.assertTrue(mainPage.isLoggedIn(firstName)); //first name appears in the login window assertion
//        additional assertion to the DB
//        Assert.assertTrue(DB.isUserExist(emailAdress));

    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
