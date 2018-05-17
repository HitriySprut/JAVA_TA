package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final SessionHelper sessionHelper = new SessionHelper();
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        sessionHelper.wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        sessionHelper.wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        sessionHelper.wd.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(sessionHelper.wd);
        navigationHelper = new NavigationHelper(sessionHelper.wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        sessionHelper.wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void gotoGroupPage() {
        navigationHelper.gotoGroupPage();
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
