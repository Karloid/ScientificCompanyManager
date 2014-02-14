package com.krld.manager.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrey on 2/10/14.
 */
@Entity
@Table(name = "authors")
public class Soldier {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Message> messages;

    public Soldier() {
    }

    public Soldier(String name) {

        setName(name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
