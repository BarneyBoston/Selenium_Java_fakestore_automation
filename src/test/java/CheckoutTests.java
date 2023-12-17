import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckoutTests extends BaseTest{
    String calculusSlug = "/calculus-made-easy-by-silvanus-p-thompson/";
    @Test
    @DisplayName("Incorrect card number check.")
    public void incorrect_card_number_should_error_appear() {
        productPage
                .go(calculusSlug)
                .addToCart()
                .storeHeader.goToCheckout();
        checkoutPage
                .inputIncorrectCardNumber()
                .placeOrder();

        Assertions.assertEquals("The card number is incomplete.",checkoutPage.getCreditCardError(),
                "Incorrect error while incorrect credit card number given.");
    }
    @Test
    @DisplayName("Correct card details without billing details check.")
    public void correct_card_details_without_billing_details_should_necessary_fields_error_appear() {
        productPage
                .go(calculusSlug)
                .addToCart()
                .storeHeader.goToCheckout();
        checkoutPage
                .inputCorrectCardNumber()
                .inputCorrectExpiryDate()
                .inputCorrectCVC()
                .placeOrder();

        Assertions.assertEquals("Billing First name is a required field.\n" +
                "Billing Last name is a required field.\n" +
                "Billing Street address is a required field.\n" +
                "Billing Postcode / ZIP is not a valid postcode / ZIP.\n" +
                "Billing Town / City is a required field.\n" +
                "Billing Phone is a required field.\n" +
                "Billing Email address is a required field.",checkoutPage.getBillingFieldsError(),"Incorrect errors for missing billing fields details.");
    }
    @Test
    @DisplayName("Correct card details and billing details check.")
    public void correct_card_details_and_billing_details_should_() {
        productPage
                .go(calculusSlug)
                .addToCart()
                .storeHeader.goToCheckout();
        checkoutPage
                .inputCorrectBillingDetails()
                .inputCorrectCardNumber()
                .inputCorrectExpiryDate()
                .inputCorrectCVC()
                .placeOrder();

        Assertions.assertEquals("Order received",checkoutPage.getOrderNotification(),"Order notification is incorrect.");
    }

}
