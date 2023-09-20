package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_posts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    public BlogPost(String title, String body) {
        this.title = title;
        this.body = body;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
//    ^^ this is to join the tables to make a relationship and the anme is the user_id being tied?
    private User user;


    //===================== Constructors ====================
    public BlogPost(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public BlogPost() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BlogPost(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}