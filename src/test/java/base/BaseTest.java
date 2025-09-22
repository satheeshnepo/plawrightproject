package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pageobjecs.LoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class BaseTest {
    public LoginPage loginPage;
    static Playwright playwright;
    Browser browser;
    Page page;
    private static Properties ORProp = new Properties();
    private static Properties configFile = new Properties();
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));
    public Page initializeBrowser() {
        playwright = Playwright.create();
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser"):"chrome";
        String headless = System.getProperty("headless") != null ? System.getProperty("headless"):"false";
        boolean headlessTypeBoolean = headless == "false" ? false : true;
        switch(browserName.toLowerCase()) {
            case "chrome":
                log.info("Browser is launched");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessTypeBoolean));
                break;
            case "firefox":
                log.info("Browser is launched");
                browser  = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(headlessTypeBoolean));
                break;
            default:
                log.info("Browser is launched");
                System.out.println("it is exec......");
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headlessTypeBoolean));
        }
        page = browser.newPage();
        return page;
    }

    @BeforeSuite
    public void setUp() {
        PropertyConfigurator.configure("./src/test/resources/log4j.properties");
        log.info("Test Execution is started ");
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/property.properties");
            ORProp.load(fis);
            log.info("OR Properties file loaded.");
        }
        catch(Throwable e) {
            e.printStackTrace();
        }
    }
    @BeforeTest
    public void launchApplication() throws IOException {

        page = initializeBrowser();
        log.info("Browser is launched");
        FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
        configFile.load(fis);
        log.info("url is loaded");
        page.navigate(configFile.getProperty("url"));
        System.out.println(page);
        loginPage = new LoginPage(page);

    }
    @AfterTest
    public void tearDown() {
        page.close();
        playwright.close();
    }
}
