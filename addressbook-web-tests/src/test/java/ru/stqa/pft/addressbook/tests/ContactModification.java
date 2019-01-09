package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().
              withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru").withGroup("test1"), true);
    }
  }

  @Test

  public void contactModificationTest() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("lukovka").withSurname("zlaya").withAddress("adr").
            withHomePhone("1243").withMobilePhone("86548").withWorkPhone("6573").withEmail("mail1@pochta.ru").withEmail2("mail2@pochta.ru").
            withEmail3("mail3@pochta.ru");    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withoutContacts(modifiedContact).withAddedContacts(contact)));
  }

}