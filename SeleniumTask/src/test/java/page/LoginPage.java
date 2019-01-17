package page;

import BaseElements.BaseElement;
import BaseElements.Button;
import BaseElements.Text;
import WebDriver.Browser;

public class LoginPage {
    Button buttonLogin = new Button("Email textbox", "//div[contains(@name, 'txtEmail')]/input");
    Text emailTextBox = new Text("Password Textbox", "//input[contains(@type, 'password')]");
    Text passwordTextBox = new Text("Password textbox", "//a[contains(@id, 'btnLogin')]");
    public BaseElement errorMessage = new BaseElement("Error MEssage",
            "//span[contains(@id, 'ctlEmailValidationBox_txtEmail-error')]");
    public Button continueButton = new Button("ContinueButton", "//a[contains(@id, 'Continue')]");

    public void login(String username, String password) {
        inputEmail(username);
        continueButton.click();
        passwordTextBox.shouldBeDisplayed();
        inputPassword(password);
        buttonLogin.click();
    }

    public void inputEmail(String email) {
        emailTextBox.inputText(email);
    }

    public void inputPassword(String password) {
        passwordTextBox.inputText(password);
    }

    public void navigateToLoginPage(String url) {
        Browser.navigateToUrl(url);
        emailTextBox.shouldBeDisplayed();
        emailTextBox.shouldBeSelected();
    }
}
