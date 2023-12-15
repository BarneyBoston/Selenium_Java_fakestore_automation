package pageobjects;

import com.sun.nio.file.ExtendedOpenOption;
import helpers.Browser;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public abstract class BasePage {
    @FindBy(css = ".blockUI")
    private List<WebElement> loadingIcons;
    protected final WebDriver driver;
    protected final Browser browser;
    protected final String baseURL;
    protected BasePage(Browser browser) {
        this.browser = browser;
        this.driver = browser.driver;
        baseURL = browser.baseURL;
        PageFactory.initElements(driver, this);
    }

    protected void waitForLoadingIconDisappear() {
        browser.wait.until(driver -> loadingIcons.size()==0);
    }
    protected void waitForAllElementsToBeDisplayed(List<WebElement> elements){
        browser.wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    protected void waitForElementToBeDisplayed(WebElement element){
        browser.wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void clickElement(WebElement element){
        waitForElementToBeDisplayed(element);
        element.click();
    }
    protected void inputText(WebElement element, String text){
        element.clear();
        element.click();
        highLightenerMethod(element);
        element.sendKeys(text);
    }
    private void highLightenerMethod(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) browser.driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: lightred; border: 5px solid red;')", element);
        waitABit(500);
    }
    protected void waitABit(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
