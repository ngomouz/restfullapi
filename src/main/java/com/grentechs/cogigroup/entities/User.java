package com.grentechs.cogigroup.entities;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
// @JsonFilter(value = "userFilter") -- For Filtering fields
// @JsonIgnoreProperties({"firstname", "lastname"}) -- for @JsonIgnore
@ApiModel(value = "User", description = "This model is to create a User")
public class User extends ResourceSupport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = Views.External.class)
    @ApiModelProperty(notes = "Auto generated unique Id", required = true, position = 1)
    private Long userId;

    @JsonView(value = Views.External.class)
    @NotEmpty(message = "The username is mandatory")
    @Column(name = "username", length = 50, unique = true, nullable = false)
    @Size(min = 5, max = 20, message = "The firstname should have atleast 2 caracteres")
    @ApiModelProperty(notes = "The username of a User", required = true, position = 2)
    private String username;

    @JsonView(value = Views.External.class)
    @Size(min = 2, max = 50, message = "The firstname should have atleast 2 caracteres")
    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;

    @JsonView(value = Views.External.class)
    @Size(min = 2, message = "The firstname should have atleast 2 caracteres")
    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "adress", nullable = true)
    private String adress;

    @Email(message = "Kindly provide a valid email")
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @JsonView(value = Views.Internal.class)
    @Column(name = "role", length = 50, nullable = false)
    private String role;

    @NotEmpty
    @NotBlank
    @JsonView(value = Views.Internal.class)
    @Column(name = "ssn", length = 50, nullable = false)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(value = Views.Internal.class)
    private Set<Order> orders = new HashSet<>();

    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String role, String ssn, String adress) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.adress = adress;
        this.role = role;
        this.ssn = ssn;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", role='" + role + '\'' +
            ", ssn='" + ssn + '\'' +
        '}';
    }
}
