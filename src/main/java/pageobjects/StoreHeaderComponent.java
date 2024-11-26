package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreHeaderComponent extends BasePage {
    @FindBy(css = "#menu-item-88 a")
    private WebElement goToWishlistFromHeader;
    @FindBy(css = ".wc-block-mini-cart__amount")
    private WebElement cartButton;

    @FindBy(xpath = "//span[text()='Go to checkout']")
    private WebElement goToCheckoutButton;
    protected StoreHeaderComponent(Browser browser) {
        super(browser);
    }

    public WishlistPage goToWishlist() {
        clickElement(goToWishlistFromHeader);
        return new WishlistPage(browser);
    }
    public CheckoutPage goToCheckout(){
        clickElement(cartButton);
        clickElement(goToCheckoutButton);
        return new CheckoutPage(browser);
    }
}
