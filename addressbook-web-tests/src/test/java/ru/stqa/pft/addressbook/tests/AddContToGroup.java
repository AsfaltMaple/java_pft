package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.hibernate.Session;
import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContToGroup extends TestBase {


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
    public void addContToGroup() { // почему на страницу групп переходит в приложении??

        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        GroupData group = groups.iterator().next();
        int groupId = group.getId();

        Contacts contactsInSelectedGroup = group.getContacts();
        //int contsInGr = contactsInSelectedGroup.size();
        if ( group.getContacts().size() == contacts.size() ) {
            app.goTo().homePage();
            ContactData newCont = new ContactData();
            app.contact().createWithoutGroup(new ContactData().withId(newCont.getId())
                    .withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru"));
        }
        app.goTo().homePage();
        Contacts newContacts = app.db().contacts();
        newContacts.removeAll(contactsInSelectedGroup);
        int contactId = newContacts.iterator().next().getId();
        app.contact().selectContactById(contactId);
        app.group().groupSelectionButton(group);
        app.contact().addToGroup();
        Contacts allContactsAfter = app.db().contacts();
        Groups groupsAfter = app.db().groups();
        GroupData selectedGr = findGroup(groupId);
        Contacts newContInG = selectedGr.getContacts();
        ContactData contact = findContact(contactId);
        app.goTo().homePage();
        MatcherAssert.assertThat(contactsInSelectedGroup.withAddedContacts(contact), equalTo(newContInG));
    }

    public ContactData findContact(int contactId) {
        ContactData cont = new ContactData();
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if ( contact.getId() == contactId )
        cont = contact;}
        return cont;
    }

    public GroupData findGroup(int groupId) {
        GroupData gr = new GroupData();
        Groups groups = app.db().groups();
        for (GroupData group : groups) {
            if ( group.getId() == groupId )
                gr = group;}
        return gr;
    }

}