package utils.driver;

import org.openqa.selenium.WebDriver;
import utils.helper.PropertyManager;

public class WebDrivers {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDrivers instance;
    private final ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
    private final PropertyManager propertyManager = new PropertyManager();

    private WebDrivers() {
    }

    public static WebDrivers getInstance() {
        if (instance == null) {
            instance = new WebDrivers();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            createDriver();
        }
        return driver.get();
    }

    private void createDriver() {
        String browserName = propertyManager.getProperty("config.properties", "browser");

        if ("Chrome".equalsIgnoreCase(browserName)) {
            driver.set(chromeWebDriver.initializeAndGetChromeDriver());
        } else {
            throw new UnsupportedOperationException("Unsupported browser: " + browserName);
        }
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}