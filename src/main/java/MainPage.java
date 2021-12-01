import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"userbar\"]/div[1]/div/div/div[1]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/header/div[2]/div/nav/ul[1]/li[2]/div")
    private WebElement popUpNews;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div/header/div[2]/div/nav/ul[1]/li[2]/a")
    private WebElement linkPopUpNews;

    public MainPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void moveToLinkPopUpNews() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(linkPopUpNews);
        actions.perform();
    }

    public boolean isVisiblePopUpNews() {
        return popUpNews.isDisplayed();
    }
}
