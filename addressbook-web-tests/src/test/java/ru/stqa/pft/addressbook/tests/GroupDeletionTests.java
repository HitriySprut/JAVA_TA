package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.gotoGroupPage();
    //check if there any group, if not - create one
    if (app.group().list().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupDeletion() {

    //list of existing groups before deletion
    List<GroupData> before = app.group().list();

    //group deletion
    int index = before.size() - 1;
    app.group().delete(index);

    //list of existing groups after deletion
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), index);
    before.remove(index);
    Assert.assertEquals(before, after);
  }




}
