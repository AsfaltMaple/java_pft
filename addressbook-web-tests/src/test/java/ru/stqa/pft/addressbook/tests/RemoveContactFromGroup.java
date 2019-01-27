package ru.stqa.pft.addressbook.tests;

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
        int sizeBefore = groups.iterator().next().getContacts().size();
        ContactData newCont = new ContactData().inGroup(selectedGr);
        //if ( sizeBefore < 1 ) {
        //    app.contact().create(contact.inGroup(selectedGr), true);
        //} else {
            app.goTo().homePage();
            app.group().groupSelectionButton(selectedGr);
            Contacts contactsInGr = app.contact().all();
            ContactData deletedContact = contactsInGr.iterator().next();
            app.contact().delete(deletedContact);
        }
       // int sizeAfter = groups.iterator().next().getContacts().size();
       // assertThat(after, equalTo(before.withoutContacts(modifiedContact).withAddedContacts(contact)));
    }


