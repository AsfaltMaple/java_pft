package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ANewContactCreation extends TestBase {

  @Test
  public void testNewContact() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("severnaya2", "Brusnika2", "brus2@sever.ru", "test1");
    app.contact().create(contact, true);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    //int max = 0;
    // for (ContactData c : after) { //переменная проходится по всем объекта грДата из списка После создания нового
    //  if (c.getId() > max) {
    //    max = c.getId();
    //   }
    // }

    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1 , c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}
