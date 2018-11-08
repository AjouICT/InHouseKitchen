package com.ajouict.inhousekitchen.domain;

import com.ajouict.inhousekitchen.controller.HttpSessionUtils;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_host"))
    private Host host;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_writer"))
    private User writer;

    @Column
    private String title;

    @Lob
    @Column
    private String contents;

    @Column
    private LocalDateTime createDate;

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public Review() {}

    public Review(User writer, Host host, String title, String contents){
        this.writer = writer;
        this.host = host;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public boolean IsSameWriter(User loginUser){
        if(loginUser == null){
            return false;
        }
        System.out.println("writer : " + this.writer.getId());
        System.out.println("login : " + loginUser.getId());

        if(this.writer.getId() != loginUser.getId()) return false;

        return true;
    }

    public void update(Review review, String title, String contents){
        review.title = title;
        review.contents = contents;
    }
}
