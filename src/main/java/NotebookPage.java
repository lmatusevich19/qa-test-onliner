import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Objects;

public class NotebookPage {
    private WebDriver webDriver;

    @FindBy(css = "schema-product__ticket-trigger_interactive")
    private List<WebElement> popUpMessage;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/div/div/div[2]/div[1]/main/div/div/aside/div[1]/div[1]/div/div[1]/div/div/a[3]")
    private WebElement putOnCart;

    public NotebookPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void clickPopUpMessage() {
        if (Objects.nonNull(popUpMessage)){
            popUpMessage.forEach(WebElement::click);
        }
    }

    public void putProductOnCart() {
        putOnCart.click();
    }
}
