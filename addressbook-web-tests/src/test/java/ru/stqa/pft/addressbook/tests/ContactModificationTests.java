package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //check if no create exists
    if (app.contact().list().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com").withGroup("someGroup"));
  }
  @Test
  public void testContactModification() {

    //list of contacts before modification
    List<ContactData> before = app.contact().list();

    //modification
    int index =before.size() - 1;
    ContactData modifiedContact = new ContactData().withId(before.get(index).getId()).withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com");
    app.contact().modify(modifiedContact,index);

    //list of contacts bafter modification
    List<ContactData> after = app.contact().list();

    before.remove(index);
    before.add(modifiedContact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }



}
