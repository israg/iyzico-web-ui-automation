package utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.helper.PropertyManager;

import java.util.HashMap;
import java.util.Map;

public class ChromeWebDriver {

    PropertyManager propertyManager = new PropertyManager();

    public WebDriver initializeAndGetChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-translate");
        options.addArguments("--always-authorize-plugins");
        options.addArguments("--disable-infobars");
        options.addArguments("--enable-automation");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);


        WebDriver driver = new ChromeDriver(options);
        driver.get(propertyManager.getProperty("config.properties", "url"));
        return driver;
    }
}
