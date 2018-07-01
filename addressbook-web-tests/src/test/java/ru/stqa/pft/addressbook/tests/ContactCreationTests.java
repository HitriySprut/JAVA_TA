package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      Contacts before = app.contact().all();
      ContactData contact = new ContactData().withFirstname("Gena1").withLastname("Krokodilov1")
              .withEmail1("gena1@gmail.com").withGroup("someGroup").withPhoto(new File("./src/test/resources/image.jpg"));
      app.contact().create(contact);
      app.goTo().HomePage();

      assertThat(app.contact().count(),equalTo(before.size()+1));
      Contacts after = app.contact().all();
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt()))));


    }


}
