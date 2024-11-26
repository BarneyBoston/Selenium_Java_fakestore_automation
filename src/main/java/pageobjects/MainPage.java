package pageobjects;

import helpers.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends BasePage {
    public final StoreHeaderComponent storeHeader;
    public final SearchToolbarComponent searchToolbar;
    public MainPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
        searchToolbar = new SearchToolbarComponent(browser);
    }
    @FindBy(css = "[name=\"orderby\"]")
    private WebElement sortingDropdown;
    @FindBy(css = "[class=\"products columns-4\"] [class=\"woocommerce-Price-amount amount\"]")
    private List<WebElement> priceElements;

    @FindBy(css = "[aria-hidden=\"true\"]:has([class=\"woocommerce-Price-amount amount\"])")
    private List<WebElement> parentElements;
    public MainPage go() {
        driver.get(baseURL);
        return this;
    }

    public MainPage chooseSortingOption(String text){
        Select select = new Select(sortingDropdown);
        select.selectByVisibleText(text);
        return this;
    }
    public List<String> getSortedPrices() {
        List<String> pricesWithoutStrikethrough = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) browser.driver;

        for (WebElement priceElement : priceElements) {
            String ariaHidden = (String) js.executeScript("return arguments[0].parentElement.ariaHidden;", priceElement);
            if (!"true".equals(ariaHidden)) {
                String priceWithoutStrikethrough = priceElement.getText();
                String price = priceWithoutStrikethrough.split(" ")[0];
                String finalPrice = price.split(",")[0];
                pricesWithoutStrikethrough.add(finalPrice);
            }
        }

        return pricesWithoutStrikethrough;
    }
    public boolean arePricesSortedFromLowToHigh(List<String> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            String currentPrice = prices.get(i);
            String nextPrice = prices.get(i + 1);

            int currentPriceInt = Integer.parseInt(currentPrice);
            int nextPriceInt = Integer.parseInt(nextPrice);

            if (currentPriceInt > nextPriceInt) {
                return false;
            }
        }

        return true;
    }

}
