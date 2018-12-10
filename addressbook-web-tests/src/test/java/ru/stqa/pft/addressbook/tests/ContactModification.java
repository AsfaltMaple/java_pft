package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

  @Test

  public void contactModificationTest() {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact() ) {
      app.getContactHelper().createContact(new ContactData(
              "Brusnika2", "severnaya2", "brus2@sever.ru","test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification ();
    app.getContactHelper().fillContactForm(
            new ContactData("luk","zloy","luk@zloy.ru",null), false);
    app.getContactHelper().submitContactModification ();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
