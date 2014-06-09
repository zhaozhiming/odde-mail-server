package com.odde.mail.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Entity(name = "recipients")
@Table(name = "recipients", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String username;

    @Basic
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
    @JsonIgnore
    private List<RecipientAttribute> recipientAttributes;

    public Recipient() {
    }

    public Recipient(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RecipientAttribute> getRecipientAttributes() {
        return recipientAttributes;
    }

    public void setRecipientAttributes(List<RecipientAttribute> recipientAttributes) {
        this.recipientAttributes = recipientAttributes;
    }

    @Override
    public String toString() {
        return format("Recipient{name='%s', email='%s'}", username, email);
    }
}
