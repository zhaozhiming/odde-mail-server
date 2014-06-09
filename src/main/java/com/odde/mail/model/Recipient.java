package com.odde.mail.model;

import javax.persistence.*;

import static java.lang.String.format;

@Entity(name = "recipients")
@Table(name = "recipients", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String name;

    @Basic
    private String email;

    public Recipient() {
    }

    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return format("Recipient{name='%s', email='%s'}", name, email);
    }
}
