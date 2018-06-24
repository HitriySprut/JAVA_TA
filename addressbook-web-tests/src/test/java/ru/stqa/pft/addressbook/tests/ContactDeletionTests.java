package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    //check if no create exists
    if (app.contact().list().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com").withGroup("someGroup"));

  }

  @Test
  public void testContactDeletion() {

    //list of contacts before deletion
    List<ContactData> before = app.contact().list();
    //deletion
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().HomePage();

    //list of contacts after deletion
    List<ContactData> after = app.contact().list();

    // assert by size
    Assert.assertEquals(after.size(), index);
    // assert by content
    before.remove(index);
    Assert.assertEquals(before, after);
  }



}
