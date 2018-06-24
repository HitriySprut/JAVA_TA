package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.gotoGroupPage();
    //list of existing groups before creation, may be empty
    List<GroupData> before = app.group().list();

    //group creation
    GroupData group = new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3");
    app.group().create(group);

    //list of existing groups after creation new one
    List<GroupData> after = app.group().list();


    Assert.assertEquals(after.size(), before.size() + 1);

    // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }

}
