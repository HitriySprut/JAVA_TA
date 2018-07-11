package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private DBhelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {//System.setProperty("webdriver.chrome.driver", "C:\\Users\\kseliumi\\IdeaProjects\\SKSES tests\\src\\main\\resources\\drivers\\chromedriver.exe");
                //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX))
                wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
            else if (browser.equals(BrowserType.IE)) wd = new InternetExplorerDriver();
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }
        dbHelper = new DBhelper();
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public void gotoGroupPage() {
        navigationHelper.groupPage();
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DBhelper db() {
        return dbHelper;
    }


}
