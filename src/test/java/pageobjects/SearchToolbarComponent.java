package pageobjects;

import helpers.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
public class SearchToolbarComponent extends BasePage{
    protected SearchToolbarComponent(Browser browser) {
        super(browser);
    }
    @FindBy(css = ".wc-block-product-search__field")
    private WebElement searchField;
    @FindBy(css = ".dashicons-arrow-right-alt2")
    private WebElement searchArrow;
    @FindBy(css = ".woocommerce-products-header__title")
    private WebElement searchResultText;
    @FindBy(css = "[class=\"products columns-4\"] [class=\"attachment-woocommerce_thumbnail size-woocommerce_thumbnail\"]")
    private List<WebElement> numberOfProductsFound;
    public void searchForContent(String text){
        inputText(searchField, text);
        clickElement(searchArrow);
    }
    public String getSearchResultText() {
        String text = searchResultText.getText();
        String[] parts = text.split("results: ");
        String searchResultText = parts[1];
        return searchResultText;
    }
    public int getNumberOfProductsFoundBySearch(){
        return numberOfProductsFound.size();
    }
}
