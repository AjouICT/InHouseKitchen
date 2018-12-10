package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.controller.HttpSessionUtils;
import com.ajouict.inhousekitchen.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    SearchRepository searchRepository;

    public void createBooking(Long hostId, String bookingDate, String bookingTime, String bookingGuest, String bookingMessage, HttpSession session){
        User user=(User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);
        Host host=searchRepository.findHostById(hostId);
        int parsedBookingGuest=Integer.parseInt(bookingGuest);
        double totalPrice1=(host.getMenusInfo().getPrice())*parsedBookingGuest;
        int totalPrice=(int)totalPrice1;

        Booking booking=new Booking(host, user, totalPrice, bookingDate, bookingTime, bookingMessage, parsedBookingGuest);
        bookingRepository.save(booking);
}

    public List<Booking> getBookingList(Long userId){
        System.out.println("Booking Controller 통과 : "+userId)   ;
        return bookingRepository.findBookingsById(userId);
    }

}
