package com.ajouict.inhousekitchen.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Date bookingDate;

    @Column
    private int bookingGuest;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", host=" + host +
                ", user=" + user +
                ", totalPrice=" + totalPrice +
                ", bookingDate=" + bookingDate +
                ", bookingGuest=" + bookingGuest +
                '}';
    }
}
