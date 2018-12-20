package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData().
                    withSurname("severnaya2").withName("Brusnika2").withEmail("brus2@sever.ru").withGroup("test1"), true);
        }
    }

    @Test

    public void contactPhoneTest() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);
    }

}
