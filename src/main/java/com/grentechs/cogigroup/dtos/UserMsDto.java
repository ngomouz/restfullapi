package com.grentechs.cogigroup.dtos;

import com.grentechs.cogigroup.entities.Order;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserMsDto implements Serializable {

    private Long userId;
    private String rolename;
    private String username;
    private String emailadress;
    private Set<Order> userOrders = new HashSet<>();

    public UserMsDto() {
    }

    public UserMsDto(Long userId, String username, String emailadress, String rolename) {
        this.userId = userId;
        this.rolename = rolename;
        this.username = username;
        this.emailadress = emailadress;
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

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(Set<Order> userOrders) {
        this.userOrders = userOrders;
    }
}
