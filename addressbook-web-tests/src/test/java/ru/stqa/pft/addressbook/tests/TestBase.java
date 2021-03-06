package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by kseliumi on 17.05.2018.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map(g -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
        /*Contacts fullUIcontacts = new Contacts();
        for (ContactData contact : uiContacts){
            fullUIcontacts.add(app.contact().infoFromEditForm(contact));
        }
        assertThat(fullUIcontacts,equalTo(dbContacts));
        */
            assertThat(uiContacts, equalTo(dbContacts.stream().map(c -> new ContactData().withId(c.getId()).withAllPhones(app.contact().mergePhones(c))
                    .withAllEmails(app.contact().mergeEmails(c)).withAddress(c.getAddress()).withFirstname(c.getFirstname())
                    .withLastname(c.getLastname()))
                    .collect(Collectors.toSet())));
        }

    }


}
