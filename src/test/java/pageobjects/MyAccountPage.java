package pageobjects;

import helpers.Browser;
import helpers.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public MyAccountPage go() {
        driver.get(baseURL + myAccountPageSlug);
        return this;
    }
    public void login (){
        inputText(usernameField,configurationReader.getUsername());
        inputText(passwordField, configurationReader.getPassword());
        clickElement(loginButton);
    }
    public String getUsername(){
        return configurationReader.getUsername();
    }
    public int getNavigationLinksListSize(){
        waitForAllElementsToBeDisplayed(navigationLinks);
        return navigationLinks.size();
    }
}
