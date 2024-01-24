package com.takeo.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    private Post post;

    // Constructors, getters, setters, and other methods
    // ...

    // Constructors
    public Comment() {
        // Default constructor
    }

    public Comment(String content, User author, Date createdAt, Post post) {
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.post = post;
    }

    // Getters and setters
    // ...
}
