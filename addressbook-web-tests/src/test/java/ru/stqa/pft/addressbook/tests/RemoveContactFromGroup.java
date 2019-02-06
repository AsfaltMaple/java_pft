package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData selectedGr = groups.iterator().next();
        if (selectedGr.getContacts().size() == 0){
            app.goTo().homePage();
            app.contact().selectContact(0);
            app.group().groupSelectionButton(selectedGr);
            app.contact().addToGroup();
           // app.contact().selectContactById(contacts.iterator().next().getId());
           // app.group().groupSelectionButton(selectedGr);
           // app.contact().addToGroup();
        }
        app.goTo().homePage();
        app.group().selectedGroupPage(selectedGr);
        Contacts contactsInGroup = selectedGr.getContacts();
        app.goTo().homePage();
        //int sizeBefore = contactsInGroup.size();
        ContactData selectedContact = app.contact().all().iterator().next();
       // app.contact().selectContactById(contactsInGroup.iterator().next().getId());
        app.group().removeFromGroup();
        app.group().returnToModGr(selectedGr);
        Contacts contactsAfter = app.db().contacts();
        Contacts contactsInGroupAfter = selectedGr.getContacts();
        //int sizeAfter = contactsInGroupAfter.size();
        MatcherAssert.assertThat(contactsInGroup.withoutContacts(selectedContact), equalTo(contactsInGroupAfter));
        //Assert.assertEquals(sizeBefore, sizeAfter - 1);
    }
}


