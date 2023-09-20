package com.codeup.codeupspringblog.models;


import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED NOT NULL AUTO_INCREMENT", nullable = false)
    private Long id;

    @Column(name = "age", columnDefinition = "tinyint(3) UNSIGNED NOT NULL", nullable = false, unique = true)
    private int age;

    @Column(name = "name", columnDefinition = "varchar(200) NOT NULL")
    private String name;


    @Column(name = "reside_state",  columnDefinition = "char(2)", nullable = true)
    private String resideState = "XX";


    public Dog() {
    }

    public Dog(Long id, int age, String name, String reside_state) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }

}