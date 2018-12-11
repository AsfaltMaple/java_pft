package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String id;
    private final String name;
    private final String surname;
    private String group;
    private final String email;

    public ContactData(String id, String name, String surname, String email, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.group = group;
    }

    public ContactData(String name, String surname, String email, String group) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.group = group;
    }

    public String getId() { return id; }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getGroup() { return group;
    }

}
