package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @Test
    public void registrationTest() throws IOException, MessagingException, javax.mail.MessagingException {
        long now = System.currentTimeMillis();
        String username = "qwe";
        String password = "password";
        String email = "solentuna@gmail.com";
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        assertTrue (app.newSession().login(username, password));
    }

}
