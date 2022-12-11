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
    MainPage mainPage;
    JoinPage joinPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        //Assume skips the test if no driver was created
        Assume.assumeNotNull(driver);
        driver.get("http://www.asos.com");

        mainPage = new MainPage(driver);
        Assume.assumeTrue(mainPage.isHomePage()); //skips the test if we got to the wrong page

        mainPage.clickMyAccountIcon();
        mainPage.clickJoin();

        joinPage = new JoinPage(driver);
        Assume.assumeTrue(joinPage.isJoinPage()); //skips the test if we got to the wrong page
    }


    @Test
    public void joinTest() throws InterruptedException {

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

    @Test
    public void joinWithoutEmailTest() throws InterruptedException {
        joinPage.fillFirstName(firstName);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthDay(1);
        joinPage.fillBirthMonth("January");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        Assert.assertTrue(joinPage.isJoinPage());
        Assert.assertTrue(joinPage.getEmailError().contains("Oops! You need to type your email here"));
//        additional assertion to the DB
//        Assert.assertFalse(DB.isUserExist(emailAdress));
    }

    @Test
    public void joinWithInvalidEmailTest() throws InterruptedException {
        joinPage.fillEmail(firstName);
        joinPage.fillFirstName(firstName);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthDay(1);
        joinPage.fillBirthMonth("January");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        Assert.assertTrue(joinPage.isJoinPage());
        Assert.assertTrue(joinPage.getEmailError().contains("Email fail! Please type in your correct email address"));
//        additional assertion to the DB
//        Assert.assertFalse(DB.isUserExist(emailAdress));
    }

    @Test
    public void joinWithoutFirstNameTest() throws InterruptedException {
        joinPage.fillEmail(emailAdress);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthDay(1);
        joinPage.fillBirthMonth("January");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        Assert.assertTrue(joinPage.isJoinPage());
        Assert.assertTrue(joinPage.getFirstNameError().contains("We need your first name"));
//        additional assertion to the DB
//        Assert.assertFalse(DB.isUserExist(emailAdress));
    }

    @Test
    public void joinWithoutbirthDayTest() throws InterruptedException {
        joinPage.fillEmail(emailAdress);
        joinPage.fillFirstName(firstName);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthMonth("January");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        Assert.assertTrue(joinPage.isJoinPage());
        Assert.assertTrue(joinPage.getBirthDayError().contains("Enter your full date of birth"));
//        additional assertion to the DB
//        Assert.assertFalse(DB.isUserExist(emailAdress));
    }

    @Test
    public void joinWithInvalidDateTest() throws InterruptedException {
        joinPage.fillEmail(emailAdress);
        joinPage.fillFirstName(firstName);
        joinPage.fillLastName(lastName);
        joinPage.filPassword(asosPassword);
        joinPage.fillBirthDay(29);
        joinPage.fillBirthMonth("February");
        joinPage.fillBirthYear(1900);
        joinPage.chooseGender(Math.round(Math.random()));
        joinPage.clickJoinAsos();

        Assert.assertTrue(joinPage.isJoinPage());
        Assert.assertTrue(joinPage.getBirthDayError().contains("Add your date of birth to get a birthday treat"));
//        additional assertion to the DB
//        Assert.assertFalse(DB.isUserExist(emailAdress));
    }


        @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        if (driver != null)
            driver.quit();
    }
}
