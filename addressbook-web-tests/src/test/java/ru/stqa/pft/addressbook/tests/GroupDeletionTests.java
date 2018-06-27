package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.gotoGroupPage();
    //check if there any group, if not - create one
    if (app.group().all().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupDeletion() {

    //all of existing groups before deletion
    Set<GroupData> before = app.group().all();

    //group deletion
    //int index = before.size() - 1;
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);

    //all of existing groups after deletion
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }





}
