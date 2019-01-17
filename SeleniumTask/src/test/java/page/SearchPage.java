package page;

import BaseElements.BaseElement;
import BaseElements.Button;
import BaseElements.Text;
import WebDriver.Browser;
import WebDriver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class SearchPage {

    public SearchPage searchByCategory(String category) {
        Browser.clickOnText(category);
        return this;
    }

    public SearchPage searchByLocation(String location) {
        Select locationSelectBox  = new Select(WebDriverManager
                .getDriver().findElement(By.xpath("//select[contains(@id,'Filter_ddlState')]")));
        locationSelectBox.selectByVisibleText(location);
        Browser.waitPageLoading();
        return this;
    }

    public SearchPage searchByDistance(String distance) {
        Select locationSelectBox  = new Select(WebDriverManager
                .getDriver().findElement(By.xpath("//select[contains(@id,'Filter_ctlLocation_ddlDistance')]")));
        locationSelectBox.selectByVisibleText(distance);
        Browser.waitPageLoading();
        return this;
    }

    public SearchPage searchByPrice(String from, String to) {
        Text fromTextBox = new Text("From TextBox", "//input[contains(@id,'txtFrom')]");
        Text toTextBox = new Text("To Textbox", "//input[contains(@id,'txtTo')]");
        fromTextBox.inputText(from);
        toTextBox.inputText(to);
        return this;
    }
}
