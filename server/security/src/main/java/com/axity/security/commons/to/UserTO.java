package com.axity.security.commons.to;

import java.io.Serializable;
public class UserTO implements Serializable{
    private int id;
    private String name;
    private String password;
    private int age;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }
}
