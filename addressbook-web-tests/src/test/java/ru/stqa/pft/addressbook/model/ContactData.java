package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String group;
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;


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

    public ContactData withGroup(String group) {
        this.group = group; return this;
    }

    public ContactData withEmail(String email) {
        this.email = email; return this;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
