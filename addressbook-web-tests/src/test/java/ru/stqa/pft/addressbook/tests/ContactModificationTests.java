package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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

    //all of contacts before modification
    Set<ContactData> before = app.contact().all();

    //modification
    //int index =before.size() - 1;
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com");
    app.contact().modify(contact);

    //all of contacts bafter modification
    Set<ContactData> after = app.contact().all();

    before.remove(modifiedContact);
    before.add(contact);
    /*Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    */
    Assert.assertEquals(before, after);


  }



}
