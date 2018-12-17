package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String name;
    private String surname;
    private String group;
    private String email;


    public ContactData withId(int id) { this.id = id; return this;}

    public ContactData withName(String name) {
        this.name = name; return this;
    }

    public ContactData withSurname(String surname) {
        this.surname = surname; return this;
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

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }
}
