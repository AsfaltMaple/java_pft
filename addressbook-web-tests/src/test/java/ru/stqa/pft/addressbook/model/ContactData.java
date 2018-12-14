package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private final String name;
    private final String surname;
    private String group;
    private final String email;

    public ContactData(int id, String surname, String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.email = null;
        this.group = null;
    }


    public ContactData(String surname, String name, String email, String group) {
        this.id = 0;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.group = group;
    }


    public void setId(int id) { this.id = id; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
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
