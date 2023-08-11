package com.codeup.codeupspringblog.models;
import jakarta.persistence.*;
@Entity
@Table( name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable =false)
    private String body;

    @Column(nullable = false)
    private String price;

}
