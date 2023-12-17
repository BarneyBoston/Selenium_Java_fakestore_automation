import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyAccountTests extends BaseTest{
    @Test
    @DisplayName("Correct login check.")
    public void admin_user_should_see_message_for_correct_login() {
        myAccountPage
                .go()
                .successfulLogin();

        Assertions.assertEquals("Hello admin (not admin? Log out)", myAccountPage.getPositiveMessageAfterLoginIn(),
                "Username doesn't match between what was input and what is displayed on the UI.");
    }
    @Test
    @DisplayName("Incorrect login check.")
    public void incorrect_user_should_see_message_for_incorrect_login() {
        myAccountPage
                .go()
                .failedLogin();

        Assertions.assertEquals("Error: The username incorrectUsername is not registered on this site." +
                        " If you are unsure of your username, try your email address instead.", myAccountPage.getFailedMessageAfterLoginIn(),
                "Incorrect message displayed for incorrect user login.");
    }
    @Test
    @DisplayName("Admins navigation links after login in check.")
    public void admin_after_login_in_should_see_7_navigation_links() {
        myAccountPage
                .go()
                .successfulLogin();

        Assertions.assertEquals(7, myAccountPage.getNavigationLinksListSize(),
                "There should be 7 navigation links available.");
    }
    @Test
    @DisplayName("Lost your password positive check.")
    public void lost_your_password_with_existing_account_username_should_send_an_e_mail() {
        myAccountPage
                .go()
                .goToLostYourPasswordLink()
                .inputCorrectUsernameOrEmail()
                .resetPassword();
        Assertions.assertEquals("Password reset email has been sent.", myAccountPage.getPositiveMessageAfterChangingPassword(),
                "Incorrect positive message after inputting existing username or e-mail");

    }
    @Test
    @DisplayName("Lost your password negative check.")
    public void lost_your_password_with_non_existing_account_username_should_not_send_an_e_mail() {
        myAccountPage
                .go()
                .goToLostYourPasswordLink()
                .inputIncorrectUsernameOrEmail()
                .resetPassword();
        Assertions.assertEquals("Invalid username or email.", myAccountPage.getFailedMessageAfterChangingPassword(),
                "Incorrect negative message after inputting non-existing username or e-mail");

    }
}
