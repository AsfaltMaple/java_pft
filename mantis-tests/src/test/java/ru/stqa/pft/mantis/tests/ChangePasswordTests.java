package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


    @Test
    public void changePasswordTest() throws Exception {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
       // String username = "qwe";
       // String password = "password";
       // String email = "solentuna@gmail.com";

        //app.registration().start(user, email);


        app.registration().loginAsAdmin();
        app.registration().listOfUsers();
        app.registration().selectUser(user);
        app.registration().resetPass();
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        assertTrue (app.newSession().login(user, password));
    }

}
