package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    //check if no create exists
    if (app.contact().all().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com").withGroup("someGroup"));

  }

  @Test
  public void testContactDeletion() {

    //all of contacts before deletion
    Set<ContactData> before = app.contact().all();
    //deletion
    int index = before.size() - 1;
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();

    //all of contacts after deletion
    Set<ContactData> after = app.contact().all();

    // assert by size
    Assert.assertEquals(after.size(), before.size() - 1);
    // assert by content
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }



}
