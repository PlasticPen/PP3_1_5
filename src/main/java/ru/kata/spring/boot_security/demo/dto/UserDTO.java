package ru.kata.spring.boot_security.demo.dto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {

    private long id;

    @NotEmpty(message = "Empty name!")
    @Size(min = 2, max = 30, message = "Name is too short or too long")
    private String name;

    @NotEmpty(message = "Empty Surname!")
    @Size(min = 2, max = 30, message = "Surbame is too short or too long")
    private String surname;

    @Column(name = "age")
    @Min(value = 0, message = "Age cannot be < 0")
    private int age;

    @Email
    @NotEmpty(message = "cannot be empty")
    private String username;

    private String password;

    private int roleId;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
