package com.axity.security.model;

import javax.persistence.*;

@Entity
@Table(name="users", schema="public")
public class UserDO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name= "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name="age")
    private int age;

    protected UserDO(){}
    public UserDO(String name, String password,int age){
        this.name=name;
        this.password=password;
        this.age=age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


