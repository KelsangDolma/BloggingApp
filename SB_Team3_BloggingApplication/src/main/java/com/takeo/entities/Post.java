package com.takeo.entities;


import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToMany
    private Set<Category> categories;

    // Constructors, getters, setters, and other methods
    // ...

    // Constructors
    public Post() {
        // Default constructor
    }

    public Post(String title, String content, User author, Date createdAt, Set<Category> categories) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.categories = categories;
    }

    // Getters and setters
    // ...
}
