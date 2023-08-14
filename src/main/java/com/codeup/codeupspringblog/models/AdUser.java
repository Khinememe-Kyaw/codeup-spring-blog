//package com.codeup.codeupspringblog.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//
//import java.util.List;
//@Entity
//@Table(name = "ad_users")
//public class AdUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false)
//    private String email;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @OneToMany
//    private List<Ad> ads;
//
//    public AdUser(long id, String email, String username, String password, List<Ad> ads) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.ads = ads;
//    }
//
//    public AdUser() {
//
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Ad> getAds() {
//        return ads;
//    }
//
//    public void setAds(List<Ad> ads) {
//        this.ads = ads;
//    }
//
//}
