package ru.stqa.pft.addressbook.tests;

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
    }

    @BeforeMethod
    public void ensurePreconditionsGr() {
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
      //  int contsInGr = contacts.size();
      //  int grSizeBefore = groups.size();
        GroupData group = groups.iterator().next();
        Contacts contactsInSelectedGroup = group.getContacts();
        int contsInGr = contactsInSelectedGroup.size();
        contacts.removeAll(contactsInSelectedGroup);
        //System.out.println(contactsInSelectedGroup);
        app.goTo().homePage();
        app.contact().selectContactById(contacts.iterator().next().getId());
        app.group().groupSelectionButton(group);
        app.contact().addToGroup();
        if ( group.getContacts().size() < contacts.size() ) {
            ContactData selectedCont = contacts.iterator().next();
            group.withContact(selectedCont);
        }
        else {ContactData newCont = new ContactData().withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru");
        group.withContact(newCont);}
      //  Contacts contactsInSelectedGroupAfter = group.getContacts();
       // int contsInGrAfter = contactsInSelectedGroup.size();

        Assert.assertEquals(contsInGr + 1, group.withId(group.getId()).getContacts().size());

    }


// Groups groups = app.db().groups();
//        app.goTo().homePage();
//        Contacts before = app.db().contacts();
//        app.contact().create(contact.inGroup(groups.iterator().next()), true);

    //List<ContactData> cont = conBefore.iterator().next();

    // List<ContactData> conWithoutGr = app.contact().

    //  for(GroupData contInGroups: )Groups grBefore  app.db().groups();


    //  ContactData movedContact = before.iterator().next();
    //new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(movedContact.getGroups().iterator().next().getName());
    //app.contact().addToGroup();
    //app.contact().addGroup(movedContact);
    //app.goTo().homePage();
    //}
    //
    //selectContactById(contact.getId());
    //  new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
    //addToGroup();
    //contactCache = null;
    //returnToHomePage();
}


//Contacts dbContacts = app.db().contacts();
//assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData().withId(c.getId())
//            .withName(c.getName()).withSurname(c.getSurname()).withAddress(c.getAddress()))
//            .collect(Collectors.toSet())));

