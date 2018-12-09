package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.controller.HttpSessionUtils;
import com.ajouict.inhousekitchen.domain.Booking;
import com.ajouict.inhousekitchen.domain.BookingRepository;
import com.ajouict.inhousekitchen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    public void createBooking(Long hostId, String bookingDate, String bookingTime, String bookingGuest, String bookingMessage, HttpSession session){
        User user=(User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        //Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(bookingDate);
        //Booking booking=new Booking(bookingDate, bookingTime, bookingGuest, bookingMessage, hostId, user.getId());
}

}
