package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //check if no create exists
    //if (app.contact().all().size()==0)
    if(app.db().contacts().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena3").withLastname("HiberPomog").withEmail1("gena@gmail.com").withGroup("someGroup").withPhoto(new File("src/test/resources/image.jpg")));

  }

  @Test
  public void testContactModification() {


    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Gena").withLastname("Krokodilov").withEmail1("gena@gmail.com").withPhoto(new File("src/test/resources/image.jpg"));
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


  }
}
