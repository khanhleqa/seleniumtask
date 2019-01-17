package testCases;

import BaseElements.Table;
import WebDriver.Browser;
import WebDriver.PropertiesUtils;
import WebDriver.WebDriverManager;
import org.testng.annotations.*;
import page.HomePage;
import page.LoginPage;
import page.SearchPage;

public class Search {
    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();
    SearchPage search = new SearchPage();

    @BeforeClass
    public void beforeClass() {
        String browser = PropertiesUtils.getPropAsString("browser");
        WebDriverManager.initLocalDriver(browser);
    }

    @Test
    public void search() {
        String url = PropertiesUtils.getPropAsString("url");
        String loginURL = PropertiesUtils.getPropAsString("login");
        String invalidUserName = PropertiesUtils.getPropAsString("invalidUserName");
        String validUser = PropertiesUtils.getPropAsString("validUserName");
        String validPassword = PropertiesUtils.getPropAsString("validPassword");
        String categoryName = PropertiesUtils.getPropAsString("categoryName");
        String location = PropertiesUtils.getPropAsString("location");
        String distance = PropertiesUtils.getPropAsString("distance");
        String priceFrom = PropertiesUtils.getPropAsString("priceFrom");
        String priceTo = PropertiesUtils.getPropAsString("priceTo");
        String numberOfRow = PropertiesUtils.getPropAsString("numberOfRow");

        homePage.launch(url);

        Browser.navigateToUrl(loginURL);
        loginPage.inputEmail(invalidUserName);
        loginPage.continueButton.click();
        loginPage.errorMessage.shouldBeDisplayed();

        loginPage.login(validUser, validPassword);

        homePage.launch(url);
        search.searchByCategory(categoryName)
                .searchByDistance(distance)
                .searchByLocation(location)
                .searchByPrice(priceFrom, priceTo);

        Table result = new Table("Table result", null);
        result.shouldHaveRowCounts(numberOfRow);
    }

    @AfterClass
    public void afterClass() {
        WebDriverManager.closeConnect();
    }
}
