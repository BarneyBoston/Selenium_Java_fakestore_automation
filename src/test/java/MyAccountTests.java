import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyAccountTests extends BaseTest{
    @Test
    @DisplayName("Admin login check.")
    public void admin_user_should_see_username_after_login_in() {
        myAccountPage
                .go()
                .login();

        Assertions.assertEquals("admin", myAccountPage.getUsername(),
                "Username doesn't match between what was input and what is displayed on the UI.");
    }
    @Test
    @DisplayName("Admin login check.")
    public void admin_after_login_in_should_see_7_navigation_links() {
        myAccountPage
                .go()
                .login();

        Assertions.assertEquals(7, myAccountPage.getNavigationLinksListSize(),
                "There should be 7 navigation links available.");
    }
}
