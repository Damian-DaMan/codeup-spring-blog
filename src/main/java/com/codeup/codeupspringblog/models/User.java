package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.List;

//This can be used for anything in the future using springboot with authentication
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", columnDefinition = "varchar(200) NOT NULL", unique = true)
    private String username;

    @Column(name = "email", columnDefinition = "varchar(200) NOT NULL", unique = true)
    private String email;

    @Column(name = "password", columnDefinition = "varchar(200) NOT NULL")
    private String password;

//    ====================

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    private List<BlogPost> blogPosts;
//    ^^^^^^^^^^ This will make the MANY the "owner" of the relationship

//    =========== constructors =================

    //================== Needed for Securtiy ---------------
    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }

    public User(long id, String username, String email, String password, List<BlogPost> blogPosts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blogPosts = blogPosts;
    }

    public User(String username, String email, String password, List<BlogPost> blogPosts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.blogPosts = blogPosts;
    }

    public User() {
    }

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
