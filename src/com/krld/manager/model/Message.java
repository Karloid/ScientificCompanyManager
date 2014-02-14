package com.krld.manager.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "msgs")
public class Message {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column
    private String message;

    @ManyToOne/*(cascade = CascadeType.ALL)*/
    private Soldier soldier;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "msg_tags", joinColumns = @JoinColumn(name = "msg_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<Tag>();

    public Message() {
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Message(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }
}
