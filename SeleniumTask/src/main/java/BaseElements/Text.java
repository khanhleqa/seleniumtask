package BaseElements;

import org.openqa.selenium.WebElement;

public class Text extends BaseElement {
    public Text(String description, String xpath) {
        super(description, xpath);
    }

    public void inputText(String text) {
        loadElement().clear();
        loadElement().sendKeys(text);
    }

    public boolean isTextClear(){
        return loadElement().getText().isEmpty();
    }
    public void clearText(){
        loadElement().clear();
        check("Textbox "+controlDescription+" should be cleared", isTextClear(), true);
    }

}
