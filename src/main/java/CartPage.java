import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    private WebDriver webDriver;

    @FindBy(className = "input-style")
    private List<WebElement> inputNumber;

    @FindBy(className = "cart-form__offers-unit_primary")
    private List<WebElement> productIntoCart;

    @FindBy(xpath = "//*[@id=\"container\"]/div[2]/div/div/div/div/div[2]/div/div[4]/div/div[1]/div[2]/div/div[4]/div/div[1]/div/a")
    private WebElement removeLastAddedProductButton;

    public CartPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public List<WebElement> getInputNumber() {
        return inputNumber;
    }

    public List<WebElement> getProductIntoCart() {
        return productIntoCart;
    }

    public void removeLastAddedProduct() {
        removeLastAddedProductButton.click();
    }
}
