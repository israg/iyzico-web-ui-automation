package pages;

import utils.helper.Action;

public class CommonPage extends Action {

    //This method verifies that the current URL matches the given 'url'.
    public void verifyUrl(String url) {
        super.verifyUrl(url);
    }

    //This method retrieves a list of elements using the 'key' and clicks on the item at the specified 'index'.
    public void getListAndClickItem(String key, int index) {
        clickElementOnList(key, index);
    }

    //This method clicks on an element identified by the 'key'.
    public void click(String key) {
        clickElement(findElement(key));
    }

    //This method scrolls to an element identified by the 'key' using a method from the parent class.
    public void scrollToElement(String key) {
        super.scrollToElement(findElement(key));
    }

    //This method checks if the text of an element identified by 'key' equals the provided 'text'.
    public void checkEquals(String key, String text) {
        checkFilledInputEquals(key, text);
    }

}
