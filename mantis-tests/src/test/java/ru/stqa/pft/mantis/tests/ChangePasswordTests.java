package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


    @Test
    public void changePasswordTest() throws Exception {
        long now = System.currentTimeMillis();
        String username = "qwe";
        String password = "password";
        String email = "solentuna@gmail.com";
        app.registration().loginAsAdmin();
        app.registration().listOfUsers();
        app.registration().selectUser(username);
        app.registration().resetPass();
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        assertTrue (app.newSession().login(username, password));
    }

}
