package com.ajouict.inhousekitchen.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    public Long id; // 리뷰 아이디

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_host"))
    private Host host; // 리뷰 호스트 유저 정보

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_writer"))
    private User writer; // 리뷰를 쓴 사람 정보

    @Column
    private String title; // 제목

    @Lob
    @Column
    private String content; // 내용

    @Column
    private LocalDateTime createDate; // 작성한 날짜

    @Column
    private Integer score;




    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }


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

    public void setContent(String content) {
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public Review() {}

    public Review(User writer, Host host, String title, String content, int score){
        this.writer = writer;
        this.host = host;
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.score = score;
    }

    public boolean IsSameWriter(User loginUser){
        if(loginUser == null){
            return false;
        }

        if(this.writer.getId() != loginUser.getId()) return false;

        return true;
    }

    public void update(Review review, String title, String content, int score){
        review.title = title;
        review.content = content;
        review.score = score;
    }
}
