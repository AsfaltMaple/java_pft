package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DeleteContact extends TestBase {


  @Test
  public void testDeleteContact() throws Exception {
    app.selectContact();
    app.deleteSelectedContacts();

  }
}
