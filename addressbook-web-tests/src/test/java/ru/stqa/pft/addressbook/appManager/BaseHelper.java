package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseHelper {

    private WebDriver wd;

    public BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void webPage() {
      wd.get("http://localhost/addressbook/index.php");
    }

    public void timeout() {
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
}
