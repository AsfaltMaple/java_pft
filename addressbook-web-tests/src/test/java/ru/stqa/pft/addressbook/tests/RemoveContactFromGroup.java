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
        int groupId = selectedGr.getId();
        //старое множ контактов в группе
        if ( selectedGr.getContacts().size() == 0 ) {
            app.goTo().homePage();
            app.contact().selectContact(0);
            app.group().groupSelectionButton(selectedGr);
            app.contact().addToGroup();
        }
        Contacts newContacts = app.db().contacts();
        Groups newGroups = app.db().groups();
        Contacts contactsInSelectedGroup = findGroup(groupId).getContacts();

        app.goTo().homePage();
        app.group().selectedGroupPage(selectedGr);
        ContactData contact = contactsInSelectedGroup.iterator().next();
        int contactId = contactsInSelectedGroup.iterator().next().getId();

        app.contact().selectContactById(contactId);
        app.group().removeFromGroup();
        app.group().returnToModGr(selectedGr);
        Contacts contactsAfter = app.db().contacts();
        Groups groupsAfter = app.db().groups();
        ContactData selectedContact = findContact(contactId);
        Contacts contactsInGroupAfter = findGroup(groupId).getContacts();

        MatcherAssert.assertThat(contactsInSelectedGroup.withoutContacts(selectedContact), equalTo(contactsInGroupAfter));
    }

    public ContactData findContact(int contactId) {
        ContactData cont = new ContactData();
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if ( contact.getId() == contactId )
                cont = contact;
        }
        return cont;
    }

    public GroupData findGroup(int groupId) {
        GroupData gr = new GroupData();
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if ( group.getId() == groupId )
                gr = group;
        }
        return gr;
    }
}


