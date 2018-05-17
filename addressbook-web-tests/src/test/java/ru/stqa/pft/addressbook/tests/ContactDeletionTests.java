package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by admin on 18.05.2018.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().gotoHomePage();
  }

}
