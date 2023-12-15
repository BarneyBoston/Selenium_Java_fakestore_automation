import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WishlistTests extends BaseTest{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    @Test
    @DisplayName("Adding one item to wishlist check.")
    public void product_added_to_wishlist_should_wishlist_have_one_item() {
        productPage
                .go(calculusSlug);
        productPage
                .addToWishlist()
                .storeHeader.goToWishlist();

        Assertions.assertEquals(1, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("No products added to wishlist check.")
    public void no_product_added_to_wishlist_should_wishlist_be_empty() {
        mainPage.
                go().
                storeHeader.goToWishlist();

        Assertions.assertEquals(0, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }
}
