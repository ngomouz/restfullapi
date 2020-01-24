package com.grentechs.cogigroup.dtos;

import com.grentechs.cogigroup.entities.Order;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDtoV2 implements Serializable {

    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private String adress;
    private String email;
    private String role;
    private Set<Order> orders = new HashSet<>();

    public UserDtoV2() {
    }

    public UserDtoV2(Long userId, String username, String firstname, String lastname, String adress, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "UserDtoV2{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", adress='" + adress + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
        '}';
    }
}
