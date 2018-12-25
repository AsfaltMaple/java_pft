package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreation extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() { //как фотку добавить??
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withSurname("severnaya1").withName("brusnika1").
            withAddress("NorthPole 1").withGroup("test1")});
    list.add(new Object[] {new ContactData().withSurname("severnaya2").withName("brusnika2").
            withAddress("NorthPole 2").withGroup("test1")});
    list.add(new Object[] {new ContactData().withSurname("severnaya3").withName("brusnika3").
            withAddress("NorthPole 3").withGroup("test1")});
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
