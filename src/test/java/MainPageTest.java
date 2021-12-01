import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
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

    //test for check popUp window
    @SneakyThrows
    @Test
    public void testPopUpNews() {
        mainPage.moveToLinkPopUpNews();
        Thread.sleep(2000);
        Assert.assertTrue(mainPage.isVisiblePopUpNews());
    }
}
