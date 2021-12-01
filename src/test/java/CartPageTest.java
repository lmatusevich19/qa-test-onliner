import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPageTest {
    private static WebDriver webDriver;

    private final CartPage cartPage = new CartPage(webDriver);
    private final MainPage mainPage = new MainPage(webDriver);
    private final LoginPage loginPage = new LoginPage(webDriver);

    private final List<String> correctBoundaryValues = Arrays.asList("1", "99");
    private final List<String> incorrectBoundaryValues = Arrays.asList("-1", "0", "100", "101");

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromeDriver"));
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.get(ConfProperties.getProperty("baseURL"));
    }

    //test for input boundary values
    @Test
    public void testInputCorrectBoundaryValues() throws InterruptedException {
        loginAndRedirectToCart();
        cartPage.getInputNumber().forEach(inputField -> correctBoundaryValues.forEach(value -> {
            inputField.clear();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inputField.sendKeys(value);
            Assert.assertEquals(inputField.getAttribute("value"), value);
        }));
    }

    //test for data entry exceeding the permissible
    @Test
    public void testIncorrectBoundaryValues() throws InterruptedException {
        loginAndRedirectToCart();
        cartPage.getInputNumber().forEach(inputField -> incorrectBoundaryValues.forEach(value -> {
            inputField.clear();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inputField.sendKeys(value);
            Assert.assertNotSame(inputField.getAttribute("value"), value);
        }));
    }

    //test remove entity
    @Test
    public void testRemoveProductOnCart() throws InterruptedException {
        loginAndRedirectToCart();
        List<WebElement> productsIntoCart = cartPage.getProductIntoCart();
        cartPage.removeLastAddedProduct();
        List<WebElement> productsIntoCartAfterRemoveLastAddedProduct = cartPage.getProductIntoCart();
        Assert.assertTrue(productsIntoCart.size() > productsIntoCartAfterRemoveLastAddedProduct.size());
    }

    public void loginAndRedirectToCart() throws InterruptedException {
        mainPage.clickSignInButton();
        loginPage.signInFromCorrectUserNameAndPassword();
        Thread.sleep(5000);
        webDriver.get(ConfProperties.getProperty("cartPageURL"));
    }
}
