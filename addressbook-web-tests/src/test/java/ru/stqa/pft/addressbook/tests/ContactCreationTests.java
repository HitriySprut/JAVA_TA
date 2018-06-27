package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      //all of contacts before creation
      Set<ContactData> before = app.contact().all();
      //creation
      //ContactData contact = new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup");
      ContactData contact = new ContactData().withFirstname("Gena").withLastname("Krokodilov").withEmail("gena@gmail.com").withGroup("someGroup");
      app.contact().create(contact);
      app.goTo().HomePage();
      //all of contacts after creation
      Set<ContactData> after = app.contact().all();

      //assert by size
      Assert.assertEquals(after.size(), before.size() + 1);

      //assert by content
      contact.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt());
      before.add(contact);
      /*Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId());
      before.sort(byId);
      after.sort(byId);
      */
      Assert.assertEquals(before, after);


    }


}
