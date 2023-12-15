import helpers.Browser;
import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pageobjects.*;

public class BaseTest {
    protected Browser browser;
    private static ConfigurationReader configuration;
    protected CartPage cartPage;
    protected MainPage mainPage;
    protected ProductPage productPage;
    protected WishlistPage wishlistPage;
    protected MyAccountPage myAccountPage;
    @BeforeAll
    public static void loadConfiguration() {
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup() {
        BrowserFactory browserFactory = new BrowserFactory();
        try {
            browser = browserFactory.createInstance(configuration);
            cartPage = new CartPage(browser);
            mainPage = new MainPage(browser);
            productPage = new ProductPage(browser);
            wishlistPage = new WishlistPage(browser);
            myAccountPage = new MyAccountPage(browser);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
    }
    @AfterEach
    public void quitDriver() {
        browser.driver.quit();
    }
}
