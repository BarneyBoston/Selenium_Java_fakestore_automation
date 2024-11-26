package pageobjects;

import helpers.Browser;
import helpers.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyAccountPage extends BasePage{
    ConfigurationReader configurationReader;
    public final StoreHeaderComponent storeHeader;
    String myAccountPageSlug = "my-account/";
    public MyAccountPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
        configurationReader = new ConfigurationReader();
    }
    @FindBy(css = "#username")
    private WebElement usernameField;
    @FindBy(css = "#password")
    private WebElement passwordField;
    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement loginButton;
    @FindBy(css = ".woocommerce-MyAccount-navigation-link")
    private List<WebElement> navigationLinks;
    @FindBy(xpath = "//a[text()='Lost your password?']")
    private WebElement lostYourPasswordLink;
    @FindBy(css = "#user_login")
    private WebElement usernameOrEmailField;

    @FindBy(css = "[value=\"Reset password\"]")
    private WebElement resetPasswordButton;
    @FindBy(css = "[class=\"woocommerce-notices-wrapper\"]>ul")
    private WebElement failedMessageAfterChangingPassword;

    @FindBy(css = "[class=\"woocommerce-message\"]")
    private WebElement positiveMessageAfterChangingPassword;
    @FindBy(css = ".woocommerce-error>li")
    private WebElement failedMessageAfterLoginIn;

    @FindBy(css = ".woocommerce-MyAccount-content>p")
    private WebElement positiveMessageAfterLoginIn;
    public MyAccountPage go() {
        driver.get(baseURL + myAccountPageSlug);
        return this;
    }
    public void successfulLogin (){
        inputText(usernameField,configurationReader.getUsername());
        inputText(passwordField, configurationReader.getPassword());
        clickElement(loginButton);
    }
    public void failedLogin (){
        inputText(usernameField,"incorrectUsername");
        inputText(passwordField, "incorrectPassword");
        clickElement(loginButton);
    }
    public int getNavigationLinksListSize(){
        waitForAllElementsToBeDisplayed(navigationLinks);
        return navigationLinks.size();
    }
    public MyAccountPage goToLostYourPasswordLink(){
        clickElement(lostYourPasswordLink);
        return this;
    }
    public MyAccountPage inputCorrectUsernameOrEmail(){
        inputText(usernameOrEmailField,configurationReader.getUsername());
        return this;
    }
    public MyAccountPage inputIncorrectUsernameOrEmail(){
        inputText(usernameOrEmailField,"notcorrectuser");
        return this;
    }
    public void resetPassword(){
        clickElement(resetPasswordButton);
    }
    public String getPositiveMessageAfterChangingPassword(){
        waitForElementToBeDisplayed(positiveMessageAfterChangingPassword);
        return positiveMessageAfterChangingPassword.getText();
    }
    public String getFailedMessageAfterChangingPassword(){
        waitForElementToBeDisplayed(failedMessageAfterChangingPassword);
        return failedMessageAfterChangingPassword.getText();
    }
    public String getFailedMessageAfterLoginIn(){
        waitForElementToBeDisplayed(failedMessageAfterLoginIn);
        return failedMessageAfterLoginIn.getText();
    }
    public String getPositiveMessageAfterLoginIn(){
        waitForElementToBeDisplayed(positiveMessageAfterLoginIn);
        return positiveMessageAfterLoginIn.getText();
    }
}
