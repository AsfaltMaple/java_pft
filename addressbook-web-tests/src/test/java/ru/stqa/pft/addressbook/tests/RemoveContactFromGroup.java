package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class RemoveContactFromGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsCont() {
        if ( app.db().contacts().size() == 0 ) {
            app.contact().create(new ContactData().
                    withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru"), true);
        }
        app.goTo().groupPage();
        if ( app.db().groups().size() == 0 ) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void removeContactFromGroup() {
        app.goTo().homePage();

        List<GroupData> groups = new ArrayList<GroupData>(app.db().groups());
        List<ContactData> contacts = new ArrayList<ContactData>(app.db().contacts());
        List<GroupData> group = new List<GroupData>(groups.iterator().next().getContacts().withoutContacts(contacts.iterator().next()));

        Assert.assertEquals(group.getContacts().size(), group.withId(group.getId()).getContacts().size() - 1);
    }
}
