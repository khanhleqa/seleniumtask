package BaseElements;

import org.openqa.selenium.JavascriptExecutor;

public class Button extends BaseElement {
    public String xpath;

    public Button(String description, String xpath) {
        super(description, xpath);
    }

    public void clickByJavascript() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", loadElement());
    }
}
