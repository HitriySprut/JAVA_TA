package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by admin on 18.05.2018.
 */
public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //check if there any group, if not - create one
    if (app.group().list().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupModification() {

    //list of existing groups before modification
    List<GroupData> before = app.group().list();

    //group modification
    int index = before.size() - 1;
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("group").withHeader("header").withFooter("footer");
    app.group().modifyGroup(group,index);

    //list of existing groups after modification
    List<GroupData> after = app.group().list();

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }

}
