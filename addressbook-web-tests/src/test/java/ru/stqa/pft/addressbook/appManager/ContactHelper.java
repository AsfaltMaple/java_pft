package ru.stqa.pft.addressbook.appManager;

import org.hibernate.SessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends BaseHelper {

    private SessionFactory sessionFactory;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])"));
    }


    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        // attach(By.name("photo"), contactData.getPhoto());


        if ( creation ) {
            if ( contactData.getGroups().size() > 0 ) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
    }

    public void fillContactFormWithoutGroup(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getSurname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
    }

    public void initNewContact() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
    }


    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void delete(int index) {
        selectContact(index);
        deleteSelectedContacts();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void create(ContactData contact, boolean b) {
        initNewContact();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void createWithoutGroup(ContactData contactWithoutGroup) {
        initNewContact();
        fillContactFormWithoutGroup(contactWithoutGroup);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]")); // строки
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            String lastname = cells.get(1).getText();
            String name = cells.get(2).getText();
            ContactData contact = new ContactData().withId(id).withSurname(lastname).withName(name);
            contacts.add(contact);
        }
        return contacts;

    }

    private Contacts contactCache = null;

    public Contacts all() {
        if ( contactCache != null ) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]")); // строки
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            String lastname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            ContactData contact = new ContactData().withId(id).withSurname(lastname).withName(name).withAllEmails(allEmails).
                    withAllPhones(allPhones);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);

    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData contactInfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).
                withEmail(email).withEmail2(email2).withEmail3(email3).
                withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();  //ок
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click(); //- ок
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click(); //- ок
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click(); //- ок
    }

    public void addToGroup() {
        click(By.name("add"));
    }




    public ContactData addGrToCont(ContactData conts) {
        if ( conts.getGroups().size() > 0 ) {
            Assert.assertTrue(conts.getGroups().size() == 1);
            List<ContactData> contacts = new ArrayList<ContactData>();
            selectContactById(conts.getId());
            return new ContactData().withName(conts.getName()).withSurname(conts.getSurname()).withHomePhone(conts.getHomePhone()).withMobilePhone(conts.getMobilePhone())
                    .withWorkPhone(conts.getWorkPhone()).withEmail(conts.getEmail()).withEmail2(conts.getEmail2()).withEmail3(conts.getEmail3());


            //for (ContactData grs : contacts) {
            //  new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(grs.getGroups().iterator().next().getName());
            //addToGroup();
            //}
        }
        return conts;
    }
}
//      groupCache.add(new GroupData().withId(id).withName(name));
// }
//ContactData cont =
// ContactData movedContact = before.iterator().next();
//new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(movedContact.getGroups().iterator().next().getName());
//selectContactById(contact.getId());
// new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
// addToGroup();
// contactCache = null;
// returnToHomePage();
//  if ( contactData.getGroups().size() > 0 ) {
//      Assert.assertTrue(contactData.getGroups().size() == 1);
//      new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
//  }
// }
// List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
//    for (WebElement element: elements ) {
//      String name = element.getText();
//      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      groupCache.add(new GroupData().withId(id).withName(name));


// public List<ContactData> list() {
//        List<ContactData> contacts = new ArrayList<ContactData>();
//        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]")); // строки
//        for (WebElement element : elements) {
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            List<WebElement> cells = element.findElements(By.cssSelector("td"));
//            String lastname = cells.get(1).getText();
//            String name = cells.get(2).getText();
//            ContactData contact = new ContactData().withId(id).withSurname(lastname).withName(name);
//            contacts.add(contact);
//        }
//        return contacts;
