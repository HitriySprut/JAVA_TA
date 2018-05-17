package ru.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTests {
    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        fillTextBox(textBoxName.user, username);
        fillTextBox(textBoxName.pass, password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testContactCreation() {

        initContactCreation();
        fillContactForm(new ContactData("Gena", "Krokodil", "gena_s_avtogenom@fairy.ft"));
        submitContactCreation();
        returnToHomepage();
    }

    private void fillTextBox(textBoxName textBoxName, String value) {

        String boxName = textBoxName.name();
        WebElement element = wd.findElement(By.name(boxName));
        element.click();
        element.clear();
        element.sendKeys(value);
    }
    enum textBoxName{
        user,
        pass,
        firstname,
        lastname,
        email,
        nickname,
        company,
        mobile
    }

    private void fillContactForm(ContactData contactData) {
        fillTextBox(textBoxName.firstname, contactData.getFirstname());
        fillTextBox(textBoxName.lastname, contactData.getLastname());
        fillTextBox(textBoxName.email, contactData.getEmail());
    }

    private void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void returnToHomepage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }



}
