import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTests extends BaseTest {
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    String historyOfAstronomySlug = "/history-of-astronomy-by-george-forbes/";

    @Test
    @DisplayName("Cart without added products is empty check.")
    public void no_product_added_to_cart_should_cart_be_empty() {
        cartPage
                .go();

        Assertions.assertEquals(0, cartPage.getNumberOfProducts(),
                "Number of products in cart is not 0.");
    }
    @Test
    @DisplayName("One product added to cart check.")
    public void product_added_to_cart_should_cart_have_one_product() {
        productPage
                    .go(calculusSlug)
                    .addToCart()
                    .goToCart();
        int numberOfProducts = cartPage
                    .getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    @DisplayName("Two products added to cart check.")
    public void two_products_added_to_cart_should_cart_have_two_products() {
                productPage
                        .go(calculusSlug)
                        .addToCart()
                        .go(historyOfAstronomySlug)
                        .addToCart()
                        .goToCart();

        int numberOfProducts = cartPage
                        .getNumberOfProducts();

        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                        "\nActual: " + numberOfProducts);
    }

    @Test
    @DisplayName("Changing quantity vs price check.")
    public void changing_quantity_in_cart_should_change_total_price() {

                productPage
                        .go(calculusSlug)
                        .addToCart()
                        .goToCart()
                        .changeQuantity(3);

        Assertions.assertEquals("39,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }

    @Test
    @DisplayName("Negative value in quantity check.")
    public void changing_quantity_in_cart_to_negative_should_not_update_total_price() {

             productPage
                .go(calculusSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("13,00 €",
                cartPage.getTotalPrice(),
                "Total price after quantity update is not what expected.");
    }
}