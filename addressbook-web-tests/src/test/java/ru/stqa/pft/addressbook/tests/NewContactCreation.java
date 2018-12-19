package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    ContactData contact = new ContactData().withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru").withGroup("test345");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    //int max = 0;
    // for (ContactData c : after) { //переменная проходится по всем объекта грДата из списка После создания нового
    //  if (c.getId() > max) {
    //    max = c.getId();
    //   }
    // }
    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

    assertThat(after, equalTo(before.withAddedContacts(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

  }

}
