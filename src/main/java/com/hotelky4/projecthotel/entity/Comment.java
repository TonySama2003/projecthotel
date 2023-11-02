package com.hotelky4.projecthotel.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    private String author;
    private String email;
    private String website;

    @Column(columnDefinition = "TEXT") // Sử dụng TEXT để lưu trữ nội dung dài hơn
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors
    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Blog blog, String author, String email, String website, String content) {
        this.blog = blog;
        this.author = author;
        this.email = email;
        this.website = website;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime now) {
    }
}