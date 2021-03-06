package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTests extends TestBase {

  //  @BeforeMethod
  //  public void ensurePreconditions () {
  //      if (app.contact().list().size() == 0) {
  //          app.contact().create(new ContactData().
  //                  withSurname("severnaya2").withName("Brusnika2").
  //                  withAddress("kosmo 24").withEmail("brus2@sever.ru").withGroup("test1"), true);
  //      }
  //  }

    @Test
    public void contactEmailsTest() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
    }
}
