package com.codeup.codeupspringblog.models;
import jakarta.persistence.*;
@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private AdUser adUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdUser getUser() {
        return adUser;
    }

    public void setUser(AdUser adUser) {
        this.adUser = adUser;
    }

    public Ad() {
    }

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Ad(String title, String description, AdUser adUser) {
        this.title = title;
        this.description = description;
        this.adUser = adUser;
    }
}