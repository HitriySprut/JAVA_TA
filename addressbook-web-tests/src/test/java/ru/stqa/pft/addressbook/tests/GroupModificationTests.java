package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by admin on 18.05.2018.
 */
public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup())
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("group1", "header", "footer"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(before,after);
  }

}
