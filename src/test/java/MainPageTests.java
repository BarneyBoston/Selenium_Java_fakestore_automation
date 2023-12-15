import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainPageTests extends BaseTest {
    @Test
    @DisplayName("Searching for product check")
    public void searching_with_how_to_do_should_show_3_results() {
        mainPage
                .go()
                .searchToolbar
                .searchForContent("how to do");
        Assertions.assertAll(
                () -> Assertions.assertEquals("“how to do”", mainPage.searchToolbar.getSearchResultText(),
                        "Text input in search field doesn't match with search result text field."),
                () -> Assertions.assertEquals(3,mainPage.searchToolbar.getNumberOfProductsFoundBySearch())
        );
    }
    @Test
    @DisplayName("Sorting products by price check")
    public void sorting_products_by_price_from_low_to_high_should_sort_appropriately() {
        mainPage
                .go()
                .chooseSortingOption("Sort by price: low to high");
        Assertions.assertTrue(mainPage.arePricesSortedFromLowToHigh(mainPage.getSortedPrices()),"Prices are not sorted from low to high.");
    }
}
