package ru.stqa.pft.mantis;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


/**
 * Created by kseliumi on 17.05.2018.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }



}
