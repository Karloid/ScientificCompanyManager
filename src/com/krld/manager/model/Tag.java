package com.krld.manager.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<com.krld.manager.model.Message> messages = new HashSet<com.krld.manager.model.Message>();

    public void setId(long id) {
        this.id = id;
    }

    public Set<com.krld.manager.model.Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<com.krld.manager.model.Message> messages) {
        this.messages = messages;
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
