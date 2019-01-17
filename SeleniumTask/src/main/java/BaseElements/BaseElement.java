package BaseElements;

import WebDriver.Browser;
import WebDriver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ReportUtils;

import java.util.List;

public class BaseElement extends WebDriverManager {
    String controlDescription;
    String xpath;

    public BaseElement(String description, String xpath) {
        this.controlDescription = description;
        this.xpath = xpath;
    }

    public void click() {
        loadElement().click();
        waitPageLoading();
    }

    public WebElement loadElement() {
        try {
            ReportUtils.writeInfoLog(controlDescription + "loading");
            WebDriverWait wait = new WebDriverWait(getDriver(), 20);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException ex) {
            return null;
        }
    }

    public List<WebElement> loadElements() {
        List<WebElement> elements = getDriver().findElements(By.xpath(xpath));
        return elements;
    }

    public BaseElement getChild(String controlDescription, String xpath) {
        return new BaseElement(controlDescription, this.xpath + xpath);
    }

    public boolean isDisplayed() {
        return loadElement().isDisplayed();
    }

    public void shouldBeDisplayed() {
        String description = "Check [" + controlDescription + "] is displayed";
        check(description, isDisplayed(), true);
    }

    public void shouldNotBeDisplayed() {
        String description = "Check [" + controlDescription + "] is NOT displayed";
        check(description, isDisplayed(), false);
    }

    public boolean isEnable() {
        return loadElement().isEnabled();
    }

    public void shouldBeEnable() {
        String description = "Check [" + controlDescription + "] is enable";
        check(description, isEnable(), true);
    }

    public void shouldBeDisabled() {
        String description = "Check [" + controlDescription + "] is disabled";
        check(description, isEnable(), false);
    }

    public String getText() {
        return loadElement().getText();
    }

    public void shouldHaveText(String text) {
        String description = "Check [" + controlDescription + "] has text: [" + text + "]";
        check(description, getText(), text);
    }

    public void shouldNotHaveText(String text) {
        String description = "Check [" + controlDescription + "] DOES NOT have text: [" + text + "]";
        check(description, getText().equals(text), false);
    }

    public void shouldContainText(String text) {
        String description = "Check [" + controlDescription + "] contains text: [" + text + "]";
        check(controlDescription, getText().contains(text), true);
    }

    public void shouldNotContainText(String text) {
        String description = "Check [" + controlDescription + "] DOES NOT contain text: [" + text + "]";
        check(description, getText().contains(text), false);
    }

    public void shouldBeSelected() {
        check(controlDescription + "should be selected", loadElement().isSelected(), true);
    }

    public String getElementAttribute(String attributeName) {
        return loadElement().getAttribute(attributeName);
    }

    public void waitPageLoading() {
        new BaseElement("Loading block ui", "//div[@id='loading-block-ui']").waitDisappear();
        Browser.waitReady(3000);
    }

    public boolean waitDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(this.xpath)));
        } catch (Exception ex) {
            return false;
        }
    }

    public void check(String description, Object actualValue, Object expectedValue) {
        if (actualValue.equals(expectedValue)) {
            ReportUtils.writeInfoLog(description);
            Assert.assertTrue(true);
        } else {
            ReportUtils.writeFailedLog(description);
            Assert.fail(description);
        }
    }
}
