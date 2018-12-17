package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreation extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    //int max = 0;
   // for (GroupData g: after) {
   //   if (g.getId() > max) {
   //     max = g.getId();
   //   }
   // }
   // group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()); - и так новый элемен будет с макс.идентификатором

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(after, before);
  }
}
