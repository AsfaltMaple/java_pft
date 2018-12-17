package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if ( app.group().list().size() == 0  ) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testDeleteGroup() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index); //вообще проходит циклом по элементам, но тестовый ФВ сам умеет сравнивать циклы в ассертИквалс
    for (int i = 0; i <after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }


    Assert.assertEquals(before, after);
  }
}
