package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if ( !app.getGroupHelper().isThereAGroup() ) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size() - 1;
    app.getGroupHelper().deletionGroup(index);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index); //вообще проходит циклом по элементам, но тестовый ФВ сам умеет сравнивать циклы в ассертИквалс
    for (int i = 0; i <after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }


    Assert.assertEquals(before, after);
  }
}
