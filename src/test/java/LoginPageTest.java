import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {
    private static WebDriver webDriver;

    private final MainPage mainPage = new MainPage(webDriver);
    private final LoginPage loginPage = new LoginPage(webDriver);

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(ConfProperties.getProperty("baseURL"));
    }

    //test use incorrect username and password
    @Test
    public void testIncorrectUserNameAndPassword() {
        webDriver.get(ConfProperties.getProperty("baseURL"));
        mainPage.clickSignInButton();
        Assert.assertNotSame(this.loginPage, loginPage.signInError());
    }

    @Test
    public void testCorrectUserNameAndPassword() {
        webDriver.get(ConfProperties.getProperty("baseURL"));
        mainPage.clickSignInButton();
        Assert.assertTrue(Objects.nonNull(loginPage.signInSuccess()));
    }
}
