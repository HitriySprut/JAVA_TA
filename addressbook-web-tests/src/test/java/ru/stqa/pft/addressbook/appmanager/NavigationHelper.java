package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by kseliumi on 17.05.2018.
 */
public class NavigationHelper extends HelperBase{
    private FirefoxDriver wd;


    public NavigationHelper(FirefoxDriver wd) {
       super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
