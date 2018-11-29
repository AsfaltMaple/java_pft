package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  private WebDriver wd;

  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    loginButton();
  }

  private void loginButton() {
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void logout() {
    logoutButton();
  }

  private void logoutButton() {
    wd.findElement(By.linkText("Logout")).click();
  }
}
