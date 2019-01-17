package WebDriver;

import BaseElements.BaseElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import utilities.ReportUtils;

import java.util.ArrayList;

public class Browser extends WebDriverManager {

    public static void navigateToUrl(String url) {
        ReportUtils.writeInfoLog("Navigate to url: " + url);
        getDriver().get(url);
        waitPageLoading();
    }

    public static void waitPageLoading() {
        new BaseElement("Loading block ui", "//div[@id='loading-block-ui']").waitDisappear();
    }

    public static void waitReady(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public static void acceptAlert() {
        try {
           getDriver().switchTo().alert().accept();
            ReportUtils.writeInfoLog("Accept alert!");
        } catch (Exception e) {
            ReportUtils.writeFailedLog(e.getMessage());
            System.out.println("No alert");
        }
    }

    public static void dismissAlert() {
        try {
           getDriver().switchTo().alert().dismiss();
        } catch (NoAlertPresentException exception) {
            ReportUtils.writeFailedLog(exception.getMessage());
            System.out.println(exception.getMessage());
        }
    }

    public static void openNewTabWithURL(String url) {
        ((JavascriptExecutor) getDriver()).executeScript("window.open();", "");
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        switchToWindow(tabs.get(1));
        getDriver().get(url);
    }

    public static void switchToWindow(String windowName) {
        getDriver().switchTo().window(windowName);
    }

    public static void closeCurrentTab(String windowName, String parentWindow) {
        switchToWindow(windowName);
        getDriver().close();
        switchToWindow(parentWindow);
    }

    public static void shouldHaveTitle(String title) {
        String actualTitle = getDriver().getTitle();
        check("Check browser title ", actualTitle, title);
    }

    private static void check(String description, Object actualValue, Object expectedValue) {
        ReportUtils.writeInfoLog("Compare with actual value: " + actualValue.toString() + "& expected Value: " + expectedValue.toString());
        if (actualValue.equals(expectedValue)) {
            ReportUtils.writeInfoLog(">>>> " + description);
            Assert.assertTrue(true);
        } else {
            ReportUtils.writeFailedLog(">>>> " + description);
            Assert.fail(description);
        }
    }
}
