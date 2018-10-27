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
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_writer"))
    private User writer;

    @Column
    private String title;

    @Lob
    @Column
    private String contents;

    @Column
    private LocalDateTime createDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
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

    public User getWriter() {
        return writer;
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

    public Review() {};

    public Review(User writer, String title, String contents){
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

    public boolean IsSameWriter(User loginUser){
        if(loginUser == null){
            return false;
        }
        return this.writer.equals(loginUser);
    }
}
