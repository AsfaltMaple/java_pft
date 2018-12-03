package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase {

    @Test
    public void testNewContact() throws Exception {

        app.getContactHelper().initNewContact();
        app.getContactHelper().fillContactForm(new ContactData(
                "Brusnika2", "severnaya2", "brus2@sever.ru","test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();

    }

}
