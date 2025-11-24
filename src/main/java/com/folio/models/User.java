package com.folio.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(nullable = false)
    private String username;

    private String designation;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Getters and Setters
    public Long getUserid() { return userid; }
    public void setUserid(Long userid) { this.userid = userid; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
