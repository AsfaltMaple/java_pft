package ru.stqa.pft.addressbook.appManager;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends BaseHelper { //помощник по работе с группами

  public GroupHelper(WebDriver wd) {
    super (wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() { click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void groupSelectionById (int id) {
    wd.findElement(By.cssSelector("input[value= '" + id + "']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void delete(GroupData group) {
    groupSelectionById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnToGroupPage();
  }

  public void modify (GroupData group) {
    groupSelectionById(group.getId());
    initGroupModification ();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Groups groupCache = null;

  public Groups all() {
    if ( groupCache != null) {
      return new Groups(groupCache);
    }

    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element: elements ) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCache);
  }

  public void groupSelectionButton(GroupData groupData) {
    click(By.name("to_group"));
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupData.getName());
  }

  public void selectedGroupPage(GroupData groupData) {
    click(By.name("group"));
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupData.getName());
  }
  public void removeFromGroup() {

    click(By.name("remove"));
   // click(By.linkText("group page \" " + groupName));
  }

  public void returnToModGr(GroupData groupData){
    click(By.linkText("group page " + "\"" + groupData.getName() + "\""));
  }

}
