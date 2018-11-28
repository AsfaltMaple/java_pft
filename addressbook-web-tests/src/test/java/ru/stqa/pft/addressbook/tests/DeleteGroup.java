package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteGroup extends TestBase {


  @Test
  public void testDeleteGroup() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().groupSelection();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
  }


}
