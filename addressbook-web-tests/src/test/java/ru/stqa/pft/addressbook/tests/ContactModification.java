package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase{

  @Test

  public void contactModificationTest() {
    if (! app.getContactHelper().isThereAContact() ) {
      app.getContactHelper().createContact(new ContactData(
              "Brusnika2", "severnaya2", "brus2@sever.ru","test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification ();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"luk","zloy","luk@zloy.ru",null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().submitContactModification ();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

   before.remove(before.size() - 1);
   before.add(contact);
   Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}