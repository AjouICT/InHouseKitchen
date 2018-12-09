package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn( foreignKey = @ForeignKey(name = "host_id") )
    private Host host;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Column
    private int totalPrice;

    @Column
    private String bookingDate;

    @Column
    private String bookingTime;

    @Column(nullable = true)
    @Lob
    private String bookingMessage;

    @Column
    private int bookingGuest;

    public Booking(Host host, User user, int totalPrice, String bookingDate, String bookingTime, String bookingMessage, int bookingGuest) {
        this.host = host;
        this.user = user;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.bookingMessage = bookingMessage;
        this.bookingGuest = bookingGuest;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", host=" + host +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", bookingDate='" + bookingDate + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", bookingMessage='" + bookingMessage + '\'' +
                ", bookingGuest=" + bookingGuest +
                '}';
    }
}
