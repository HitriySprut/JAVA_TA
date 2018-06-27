package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.gotoGroupPage();
    //all of existing groups before creation, may be empty
    Set<GroupData> before = app.group().all();

    //group creation
    GroupData group = new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3");
    app.group().create(group);

    //all of existing groups after creation new one
    Set<GroupData> after = app.group().all();


    Assert.assertEquals(after.size(), before.size() + 1);

    // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    group.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt());
    before.add(group);
    /*Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    */
    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(group)));

  }

}
