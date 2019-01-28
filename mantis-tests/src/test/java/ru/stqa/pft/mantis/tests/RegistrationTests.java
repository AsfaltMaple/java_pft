package ru.stqa.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    //для теста James отключить
    //@BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void registrationTest() throws IOException, MessagingException, javax.mail.MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);
        app.james().createUser(user, password); //для james test
        app.registration().start(user, email);
        //для встроенного включить
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 40000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 80000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    //для теста James отключить
    //@AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
