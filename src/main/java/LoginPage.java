import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@NoArgsConstructor
public class LoginPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id=\"auth-container\"]/div/div[2]/div/form/div[1]/div/div[2]/div/div/div/div/input")
    private WebElement userName;

    @FindBy(xpath = "//*[@id=\"auth-container\"]/div/div[2]/div/form/div[2]/div/div/div/div/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"auth-container\"]/div/div[2]/div/form/div[3]/button")
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void signInFromCorrectUserNameAndPassword() {
        userName.sendKeys(ConfProperties.getProperty("correctUserName"));
        password.sendKeys(ConfProperties.getProperty("correctPassword"));
        signInButton.click();
    }

    public void signInFromIncorrectUserNameAndPassword() {
        userName.sendKeys(ConfProperties.getProperty("invalidUserName"));
        password.sendKeys(ConfProperties.getProperty("invalidPassword"));
        signInButton.click();
    }

    public MainPage signInSuccess() {
        signInFromCorrectUserNameAndPassword();
        return new MainPage(webDriver);
    }

    public LoginPage signInError() {
        signInFromIncorrectUserNameAndPassword();
        return new LoginPage(webDriver);
    }
}
