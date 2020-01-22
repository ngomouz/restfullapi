package com.grentechs.cogigroup.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The username is mandatory")
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Size(min = 2, message = "The firstname should have atleast 2 caracteres")
    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @Size(min = 2, message = "The firstname should have atleast 2 caracteres")
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Email(message = "Kindly provide a valid email")
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @NotEmpty
    @NotBlank
    @Column(name = "ssn", length = 50, nullable = false)
    private String ssn;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders = new HashSet<>();

    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String role, String ssn) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            ", ssn='" + ssn + '\'' +
        '}';
    }
}
