package com.grentechs.cogigroup.dtos;

import com.grentechs.cogigroup.entities.Order;

import java.io.Serializable;
import java.util.Set;

public class UserMmDto implements Serializable {

    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private Set<Order> orders;

    public UserMmDto() {
    }

    public UserMmDto(Long userId, String username, String firstname, String lastname) {
        this.userId = userId;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
