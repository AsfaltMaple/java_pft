package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class NewContactCreation extends TestBase {

    @Test
    public void testNewContact() throws Exception {

        initNewContact();
        fillNewContactForm(new ContactData("Brusnika2", "Severnaya2", "brus2@sever.ru"));
        submitContactCreation();
        returnToHomePage();

        initNewContact();
        fillNewContactForm(new ContactData("Brusnika3", "Severnaya2", "brus2@sever.ru"));
        submitContactCreation();
        returnToHomePage();

    }

}
