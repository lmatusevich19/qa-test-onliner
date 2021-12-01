import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotebookPageTest {
    private static WebDriver webDriver;

    private final NotebookPage notebookPage = new NotebookPage(webDriver);
    private final MainPage mainPage = new MainPage(webDriver);
    private final LoginPage loginPage = new LoginPage(webDriver);
    private final CartPage cartPage = new CartPage(webDriver);

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(ConfProperties.getProperty("baseURL"));
    }

    //test for check popUp message
    @Test
    public void testPopUpMessage() {
        webDriver.get(ConfProperties.getProperty("catalogNotebookURL"));
        notebookPage.clickPopUpMessage();
    }

    //test create entity
    @Test
    public void testPutProductOnCart() throws InterruptedException {
        loginAndRedirectToCart();
        List<WebElement> productsIntoCart = cartPage.getProductIntoCart();
        webDriver.get(ConfProperties.getProperty("notebookLGGramURL"));
        Thread.sleep(5000);
        notebookPage.putProductOnCart();
        webDriver.get(ConfProperties.getProperty("cartPageURL"));
        Thread.sleep(5000);
        List<WebElement> productsIntoCartAfterAddNewProduct = cartPage.getProductIntoCart();
        Assert.assertTrue(productsIntoCart.size() < productsIntoCartAfterAddNewProduct.size());
    }

    public void loginAndRedirectToCart() throws InterruptedException {
        mainPage.clickSignInButton();
        loginPage.signInFromCorrectUserNameAndPassword();
        Thread.sleep(5000);
        webDriver.get(ConfProperties.getProperty("cartPageURL"));
    }
}
