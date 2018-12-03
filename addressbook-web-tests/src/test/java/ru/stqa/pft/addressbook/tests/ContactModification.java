package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

  @Test

  public void contactModificationTest() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification ();
    app.getContactHelper().fillContactForm(
            new ContactData("luk","zloy","luk@zloy.ru",null), false);
    app.getContactHelper().submitContactModification ();
    app.getContactHelper().returnToHomePage();
  }
}
