package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by admin on 18.05.2018.
 */
public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    //check if there any group, if not - create one
    if (app.group().all().size()==0)
      app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));

  }

  @Test
  public void testGroupModification() {

    //all of existing groups before modification
    Set<GroupData> before = app.group().all();

    //group modification
    int index = before.size() - 1;
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("group").withHeader("header").withFooter("footer");
    app.group().modify(group);

    //all of existing groups after modification
    Set<GroupData> after = app.group().all();

    before.remove(modifiedGroup);
    before.add(group);
    /*Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    */
    Assert.assertEquals(before, after);


  }

}
