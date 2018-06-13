package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact())
            app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification();
        ContactData modifiedContact = new ContactData(before.get(0).getId(),"Gennadiy", "El Croco", "modifiedcontact@viva.me", null);
        app.getContactHelper().fillContactForm(modifiedContact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(0);
        before.add(modifiedContact);

        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


    }

}
