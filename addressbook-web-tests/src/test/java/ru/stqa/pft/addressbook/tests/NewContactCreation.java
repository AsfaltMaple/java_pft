package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreation extends TestBase {

    @Test
    public void testNewContact() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData(
                "Brusnika2", "severnaya2", "brus2@sever.ru","test1"), true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);

    }

}
