package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{
    public CheckoutPage checkoutPage;

    public CheckoutPage(Browser browser) {
        super(browser);
    }
    @FindBy(css = "[class=\"InputElement is-empty Input Input--empty\"][name=\"cardnumber\"]")
    private WebElement cardNumberField;
    @FindBy(css = "#place_order")
    private WebElement placeOrderButton;
    @FindBy(css = ".woocommerce_error>li")
    private WebElement creditCardError;
    @FindBy(css = "#stripe-card-element iframe")
    private WebElement creditCardNumberFrame;
    @FindBy(css = "[name=\"exp-date\"]")
    private WebElement expiryDateField;
    @FindBy(css = "#stripe-exp-element iframe")
    private WebElement expiryDateFrame;
    @FindBy(css = "[name=\"cvc\"]")
    private WebElement cvcField;
    @FindBy(css = "#stripe-cvc-element iframe")
    private WebElement cvcFrame ;
    @FindBy(css = "[class=\"woocommerce-error\"]")
    private WebElement billingFieldsError ;
    @FindBy(css = "[class=\"entry-title\"]")
    private WebElement orderNotification ;
    private final String checkoutSlug = "checkout/";
    public CheckoutPage go() {
        driver.get(baseURL + checkoutSlug);
        return this;
    }
    public CheckoutPage inputIncorrectCardNumber(){
        waitForElementToBeDisplayed(creditCardNumberFrame);
        browser.driver.switchTo().frame(creditCardNumberFrame);
        inputText(cardNumberField,"123456");
        browser.driver.switchTo().defaultContent();
        return this;
    }
    public void placeOrder(){
        clickElement(placeOrderButton);
    }
    public String getCreditCardError(){
        waitForElementToBeDisplayed(creditCardError);
        return creditCardError.getText();
    }
    public CheckoutPage inputCorrectCardNumber(){
        waitForElementToBeDisplayed(creditCardNumberFrame);
        browser.driver.switchTo().frame(creditCardNumberFrame);
        inputText(cardNumberField,"4242424242424242");
        browser.driver.switchTo().defaultContent();
        return this;
    }
    public CheckoutPage inputCorrectExpiryDate(){
        waitForElementToBeDisplayed(expiryDateFrame);
        browser.driver.switchTo().frame(expiryDateFrame);
        inputText(expiryDateField,"1026");
        browser.driver.switchTo().defaultContent();
        return this;
    }
    public CheckoutPage inputCorrectCVC(){
        waitForElementToBeDisplayed(cvcFrame);
        browser.driver.switchTo().frame(cvcFrame);
        inputText(cvcField,"123");
        browser.driver.switchTo().defaultContent();
        return this;
    }
    public String getBillingFieldsError(){
        waitForElementToBeDisplayed(billingFieldsError);
        return billingFieldsError.getText();
    }
    public CheckoutPage inputCorrectBillingDetails(){
        driver.findElement(By.cssSelector("#billing_first_name")).sendKeys("Adam");
        driver.findElement(By.cssSelector("#billing_last_name")).sendKeys("Nowak");
        driver.findElement(By.cssSelector("#billing_address_1")).sendKeys("ul. XYZ 1");
        driver.findElement(By.cssSelector("#billing_postcode")).sendKeys("11-123");
        driver.findElement(By.cssSelector("#billing_city")).sendKeys("Sopot");
        driver.findElement(By.cssSelector("#billing_phone")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#billing_email")).sendKeys("test@test.pl");
        return this;
    }
    public String getOrderNotification(){
        waitForLoadingIconDisappear();
        waitForElementToBeDisplayed(orderNotification);
        return orderNotification.getText();
    }

}
