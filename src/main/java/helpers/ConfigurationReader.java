package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class ConfigurationReader {
    private String browser;
    private String baseURL;
    private String headless;
    private String waitInSeconds;
    private String username;
    private String password;
    private final String propertyNotSpecifiedMessage = "is not specified in the Configuration.properties file.";

    public ConfigurationReader() {
        String configurationPath = "src/test/resources/configuration.properties";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(configurationPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found at: " + configurationPath);
        }
        Properties properties = new Properties();
        try {
            properties.load(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        baseURL = properties.getProperty("baseURL");
        browser = properties.getProperty("browser");
        headless = properties.getProperty("headless");
        waitInSeconds = properties.getProperty("waitInSeconds");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public String getBrowser() {
        if (!browser.isEmpty()) return browser;
        else throw new RuntimeException("\"browser\" " + propertyNotSpecifiedMessage);
    }
    public boolean isHeadless() {
        if (!headless.isEmpty()) return Boolean.parseBoolean(headless);
        else throw new RuntimeException("\"headless\" " + propertyNotSpecifiedMessage);
    }
    public String getBaseUrl() {
        if (!baseURL.isEmpty()) return baseURL;
        else throw new RuntimeException("\"baseUrl\" " + propertyNotSpecifiedMessage);
    }

    public int getWaitInSeconds() {
        if (!waitInSeconds.isEmpty()) return Integer.parseInt(waitInSeconds);
        else throw new RuntimeException("\"waitInSeconds\" " + propertyNotSpecifiedMessage);
    }
    public String getUsername() {
        if (!username.isEmpty()) return decodeString(username);
        else throw new RuntimeException("\"waitInSeconds\" " + username);
    }
    public String getPassword() {
        if (!password.isEmpty()) return decodeString(password);
        else throw new RuntimeException("\"waitInSeconds\" " + password);
    }
    private String decodeString(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        return new String(decodedBytes);
    }
}
