package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeleteContact extends TestBase{
  private boolean acceptNextAlert = true;


  @Test
  public void testDeleteContact() throws Exception {
    selectContact();
    acceptNextAlert = true;
    deleteSelectedContacts();
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = wd.switchTo().alert();
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
