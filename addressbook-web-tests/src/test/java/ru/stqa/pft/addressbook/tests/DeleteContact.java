package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class DeleteContact extends TestBase {
  private boolean acceptNextAlert = true;


  @Test
  public void testDeleteContact() throws Exception {
    app.selectContact();
    acceptNextAlert = true;
    app.deleteSelectedContacts();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = app.wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
