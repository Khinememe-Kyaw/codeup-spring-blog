package com.codeup.codeupspringblog.models;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Entity;

// Model..These build out the tables in DB
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String body;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    public Post(String title, String body, User creator) {
        this.title = title;
        this.body = body;
        this.creator = creator;
    }

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}