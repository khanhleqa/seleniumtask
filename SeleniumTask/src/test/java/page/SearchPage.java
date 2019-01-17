package page;

import BaseElements.BaseElement;
import BaseElements.Button;
import BaseElements.Text;
import org.openqa.selenium.support.ui.Select;

public class SearchPage {
    Text searchText = new Text("search Text Box", "");
    Button searchButton = new Button("search Button", "//a[contains(@id, 'Filter_btnSearch')]");
    BaseElement sortSelectBox = new BaseElement("Sort Select Box", "");

    public SearchPage searchByCategory(String category) {
        BaseElement categoryLocator = new BaseElement("Category", "//li[contains(.='" + category + "')]");
        categoryLocator.click();
        return this;
    }

    public SearchPage searchByLocation(String location) {
        BaseElement locationLocator = new BaseElement("Location selectBox", "//label[.='Kanton']/following::select[1]");
        Select locationSelectBox  = (Select) locationLocator.loadElement();
        locationSelectBox.selectByValue(location);
        searchButton.click();
        return this;
    }

    public SearchPage searchByDistance(String distance) {
        BaseElement locationLocator = new BaseElement("Location selectBox", "//select[contains(@id,'Filter_ctlLocation_ddlDistance')]");
        Select locationSelectBox  = (Select) locationLocator.loadElement();
        locationSelectBox.selectByValue(distance);
        searchButton.click();
        return this;
    }

    public SearchPage searchByPrice(String from, String to) {
        Text fromTextBox = new Text("From TextBox", "//input[contains(,.'txtFrom')]");
        Text toTextBox = new Text("To Textbox", "//input[contains(,.'txtTo')]");
        fromTextBox.inputText(from);
        toTextBox.inputText(to);
        searchButton.click();
        return this;
    }
}
