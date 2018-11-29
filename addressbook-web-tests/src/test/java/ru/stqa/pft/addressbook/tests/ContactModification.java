package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactModification extends TestBase{

  @Test

  public void contactModificationTest() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification ();
    app.getContactHelper().submitContactModification ();
    app.getContactHelper().returnToHomePage();
  }
}
