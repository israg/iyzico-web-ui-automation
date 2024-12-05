package utils.helper;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Action extends Helper {

    Actions actions;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    public Action() {
        actions = new Actions(webDriver);
    }

    //This method teardown the driver
    public void tearDown() {
        webDriver.quit();
    }

    //This method verifies current url with given url
    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    //This method finds WebElementLists that is created by key
    public List<WebElement> findElements(String key) {
        return webDriver.findElements(getBy(key));
    }

    //This method click on List element with given key and index
    public void clickElementOnList(String key, int index) {
        List<WebElement> elements = findElements(key);
        if (index >= 1 && index <= elements.size()) {
            elements.get(index - 1).click();
        } else {
            Assert.fail("Index is out of bounds");
        }
    }

    //This method finds element with given key
    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 20000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        return null;
    }

    //This method clicks on element with given element
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    //This method makes the thread wait for a certain while
    public void waitFor(double duration) {
        waitForGivenTime(duration);
    }

    //This method scrolls to the given element
    public void scrollToElement(WebElement element) {
        scrollWithJS(element);
    }

    //This method checks if the input field identified by 'key' has the value equal to the given 'text'
    public void checkFilledInputEquals(String key, String text) {
        try {
            Assert.assertTrue(findElement(key).getText().equalsIgnoreCase(text));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //This method clear and fill text field with given text
    public void clearAndFillInput(String key, String text) {
        centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).clear();
        centerElement(waitUntilElementIsVisible(findElement(key), System.currentTimeMillis())).sendKeys(text);
    }

    //This method fills the OTP input field by extracting the OTP code from an SMS text and then submits the form by clicking the submit button.
    public void fillOTP() {
        try {
            WebElement smsCodeInput = findElementWithTimeout("smsCodeInput", 5);  // 10 saniye bekle
            if (smsCodeInput != null && smsCodeInput.isDisplayed()) {
                String smsText = findElement("smsText").getText();
                String regex = "\\((\\d{6})\\)";  // A regex that will match a 6-digit number inside parentheses
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(smsText);

                if (matcher.find()) {
                    String smsCode = matcher.group(1);
                    clearAndFillInput("smsCodeInput", smsCode);
                    clickElement(findElement("submitButton"));
                } else {
                    System.out.println("SMS code not found!");
                }
            }
        } catch (Exception e) {
            System.out.println("OTP screen is not visible or an error occurred: " + e.getMessage());
        }
    }

    //This method find an element within a given timeout duration.
    public WebElement findElementWithTimeout(String key, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(key)));
        } catch (TimeoutException e) {
            return null;
        }
    }
}
