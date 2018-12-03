package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class DeleteContact extends TestBase {


  @Test
  public void testDeleteContact() throws Exception {
    if (! app.getContactHelper().isThereAContact() ) {
      app.getContactHelper().createContact(new ContactData(
              "Brusnika2", "severnaya2", "brus2@sever.ru","test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToHomePage();

  }
}
