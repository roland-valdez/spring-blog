package com.codeup.springblog.models;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false)
    private String body;

    @OneToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    public Post(){};

    public Post(long id, String title, String body, User owner, List<Image> images) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.images = images;
    }

    public Post(String title, String body, User owner, List<Image> images) {
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.images = images;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
