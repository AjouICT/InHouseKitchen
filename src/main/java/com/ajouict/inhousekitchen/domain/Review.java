package com.ajouict.inhousekitchen.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_writer"))
    private User writer;

    private String title;

    @Lob
    private String contents;

    private LocalDateTime createDate;

    public Review() {};

    public Review(User writer, String title, String contents){
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = LocalDateTime.now();
    }

}
