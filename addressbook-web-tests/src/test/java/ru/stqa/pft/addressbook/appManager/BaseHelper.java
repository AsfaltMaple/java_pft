package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.WebDriver;

public class BaseHelper {
    private WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void webPage() {
      wd.get("http://localhost/addressbook/index.php");
    }
}
