package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public Browser createInstance(ConfigurationReader configurationReader) throws NoSuchBrowserException {
        WebDriver driver = createDriverInstance(configurationReader);
        return new Browser(driver, configurationReader);
    }
    private WebDriver createDriverInstance(ConfigurationReader configuration) throws NoSuchBrowserException {
        String browser = configuration.getBrowser();

        switch (browser) {
            case "firefox" -> {
                return createFirefoxInstance(configuration);
            }
            case "chrome" -> {
                return createChromeInstance(configuration);
            }
            case "edge" -> {
                return createEdgeInstance(configuration);
            }
            default -> throw new NoSuchBrowserException(browser);
        }
    }
    private WebDriver createChromeInstance(ConfigurationReader configuration) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        if (configuration.isHeadless()) {
            return new ChromeDriver(options.addArguments("--headless=new"));
        } else {
            return new ChromeDriver(options);
        }
    }

    private WebDriver createFirefoxInstance(ConfigurationReader configuration) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        if (configuration.isHeadless()) {
            options.addArguments("--headless");
            return new FirefoxDriver(options);
        } else {
            return new FirefoxDriver(options);
        }
    }

    private WebDriver createEdgeInstance(ConfigurationReader configuration) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        if (configuration.isHeadless()) {
            options.addArguments("--headless=new");
            return new EdgeDriver(options);
        } else {
            return new EdgeDriver(options);
        }
    }
}
