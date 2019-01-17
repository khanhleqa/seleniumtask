package WebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static void initLocalDriver(String browser) {
        if (browser.toLowerCase().equals("firefox")) {
            FirefoxDriverManager.firefoxdriver().setup();
            webDriver.set(new FirefoxDriver());
        } else if (browser.toLowerCase().equals("chrome")) {
            ChromeDriverManager.chromedriver().setup();
            webDriver.set(new ChromeDriver());
        } else if (browser.toLowerCase().equals("safari")) {
            webDriver.set(new SafariDriver());
        } else if (browser.toLowerCase().equals("ie")) {
            InternetExplorerDriverManager.iedriver().setup();
            webDriver.set(new InternetExplorerDriver());
        } else if (browser.toLowerCase().equals("edge")) {
            EdgeDriverManager.edgedriver().setup();
            webDriver.set(new EdgeDriver());
        }
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void closeConnect() {
        getDriver().quit();
    }
}
