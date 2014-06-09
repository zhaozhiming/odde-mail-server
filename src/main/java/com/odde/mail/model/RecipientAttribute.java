package com.odde.mail.model;

import javax.persistence.*;

import static java.lang.String.format;

@Entity(name = "recipientAttributes")
public class RecipientAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "rid")
    private Recipient recipient;

    @Basic
    private String keyItem;

    @Basic
    private String valueItem;

    public RecipientAttribute() {
    }

    public RecipientAttribute(Recipient recipient, String keyItem, String valueItem) {
        this.recipient = recipient;
        this.keyItem = keyItem;
        this.valueItem = valueItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(String key) {
        this.keyItem = key;
    }

    public String getValueItem() {
        return valueItem;
    }

    public void setValueItem(String value) {
        this.valueItem = value;
    }

    @Override
    public String toString() {
        return format("RecipientAttribute{recipient=%s, key='%s', value='%s'}", recipient, keyItem, valueItem);
    }
}
