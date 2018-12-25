package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException { //как фотку добавить??
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new ContactData().withName(split[0]).withSurname(split[1]).withAddress(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContacts") //как фотку добавить?
  public void testNewContact(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/574.png");
    app.contact().create(contact, true);
    app.goTo().homePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAddedContacts(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

//добавить проверку картинки
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
