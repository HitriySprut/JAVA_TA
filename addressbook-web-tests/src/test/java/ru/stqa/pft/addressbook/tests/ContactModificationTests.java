package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //check if no create exists
    if (app.contact().all().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com").withGroup("someGroup"));
  }
  @Test
  public void testContactModification() {


    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com");
    app.contact().modify(contact);
    Contacts after = app.contact().all();

    Assert.assertEquals(before, after.without(modifiedContact).withAdded(contact));


  }



}
