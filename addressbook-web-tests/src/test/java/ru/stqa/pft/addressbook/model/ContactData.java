package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {

    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String group;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String address;
    private String email2;
    private String email3;
    private String allPhones;
    private String allEmails;
    private File photo;



    public ContactData withId(int id) { this.id = id; return this;}
    public ContactData withName(String name) {
        this.name = name; return this;
    }
    public ContactData withSurname(String surname) {
        this.surname = surname; return this;
    }
    public ContactData withHomePhone(String home) {
        this.homePhone = home; return this;
    }
    public ContactData withMobilePhone(String mobile) {
        this.mobilePhone = mobile; return this;
    }
    public ContactData withWorkPhone(String work) {
        this.workPhone = work; return this;
    }
    public ContactData withAddress(String address) {
        this.address = address; return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2; return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3; return this;
    }
    public ContactData withGroup(String group) {
        this.group = group; return this;
    }
    public ContactData withEmail(String email) {
        this.email = email; return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones; return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails; return this;
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo; return this;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }


    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return group;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public File getPhoto() { return photo; }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }


}
