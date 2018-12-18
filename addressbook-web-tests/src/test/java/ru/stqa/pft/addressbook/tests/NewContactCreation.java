package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NewContactCreation extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru").withGroup("test345");
    app.contact().create(contact, true);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    //int max = 0;
    // for (ContactData c : after) { //переменная проходится по всем объекта грДата из списка После создания нового
    //  if (c.getId() > max) {
    //    max = c.getId();
    //   }
    // }

    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
