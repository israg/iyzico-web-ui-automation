package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.CommonPage;

public class CommonSteps {

    CommonPage commonPage;

    @Before
    public void setUp() {
        commonPage = new CommonPage();
    }

    @After
    public void tearDown() {
        commonPage.tearDown();
    }

    @Given("Check page url contains {string}")
    public void checkPageUrlContains(String url) {
        commonPage.verifyUrl(url);
    }

    @And("Get list for {string} click {int} item")
    public void getListForClickItem(String key, int index) {
        commonPage.getListAndClickItem(key, index);
    }

    @And("Click {string}")
    public void click(String key) {
        commonPage.click(key);
    }

    @And("Wait for given seconds {int}")
    public void waitForGivenSeconds(int seconds) {
        commonPage.waitFor(seconds);
    }

    @And("Scroll to {string} element")
    public void scrollToElement(String key) {
        commonPage.scrollToElement(key);
    }

    @Then("Check equality of {string} field with {string}")
    public void checkByToWith(String key, String text) {
        commonPage.checkEquals(key, text);
    }
}

