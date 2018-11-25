package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase {

    @Test
    public void testNewContact() throws Exception {

        app.initNewContact();
        app.fillNewContactForm(new ContactData("Brusnika2", "Severnaya2", "brus2@sever.ru"));
        app.submitContactCreation();
        app.returnToHomePage();

        app.initNewContact();
        app.fillNewContactForm(new ContactData("Brusnika3", "Severnaya2", "brus2@sever.ru"));
        app.submitContactCreation();
        app.returnToHomePage();

    }

}
