package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/574.png");
    ContactData contact = new ContactData().withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAddedContacts(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void currentDir (){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/574.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)
  public void testNewBadContact() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withSurname("severnaya2").withName("Brusnika2'").withEmail("brus2@sever.ru").withGroup("test345");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }

}
