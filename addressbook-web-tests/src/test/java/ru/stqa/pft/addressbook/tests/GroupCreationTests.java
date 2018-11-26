package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appManager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests {


    private final ApplicationManager app = new ApplicationManager();

    @Test
    public void testGroupCreation() throws Exception {

        app.gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitCreationGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
