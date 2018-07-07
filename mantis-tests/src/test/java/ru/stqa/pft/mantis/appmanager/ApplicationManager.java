package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final String browser;
    private final Properties properties;
    WebDriver wd;


    public ApplicationManager(String browser)  {
        this.browser=browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

        if(browser.equals(BrowserType.CHROME))
        {//System.setProperty("webdriver.chrome.driver", "C:\\Users\\kseliumi\\IdeaProjects\\SKSES tests\\src\\main\\resources\\drivers\\chromedriver.exe");
            //System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        wd=new ChromeDriver();}
        else if (browser.equals(BrowserType.FIREFOX) ) wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        else if (browser.equals(BrowserType.IE))wd = new InternetExplorerDriver();

        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }



}
